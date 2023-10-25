package taller.grado.proyectofinalbackend.jwt;

import com.nimbusds.jwt.JWT;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.JWTParser;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import taller.grado.proyectofinalbackend.config.ApplicationProperties;
import taller.grado.proyectofinalbackend.model.Authority;
import taller.grado.proyectofinalbackend.model.dto.JwtDTO;
import taller.grado.proyectofinalbackend.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.security.Key;
import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class JwtProvider {

    private final Logger log = LoggerFactory.getLogger(JwtProvider.class);

    private static final String AUTHORITIES_KEY = "auth";

    private Key key;

    private long tokenValidityInMilliseconds;

    private long tokenValidityInMillisecondsForRememberMe;

    private final ApplicationProperties applicationProperties;

    private final UserRepository userRepository;


    public JwtProvider(ApplicationProperties applicationProperties, UserRepository userRepository) {
        this.applicationProperties = applicationProperties;
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void init() {
        byte[] keyBytes;
        log.debug("Using a Base64-encoded JWT secret key");
        keyBytes = Decoders.BASE64.decode(applicationProperties.getJwt().getBase64Secret());
        this.key = Keys.hmacShaKeyFor(keyBytes);
        this.tokenValidityInMilliseconds =
                1000 * applicationProperties.getJwt().getTokenValidityInSeconds();
        this.tokenValidityInMillisecondsForRememberMe =
                1000 * applicationProperties.getJwt().getTokenValidityInSecondsForRememberMe();
    }

    public String createToken(Authentication authentication, boolean rememberMe) {
        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        long now = (new Date()).getTime();
        Date validity;
        if (rememberMe) {
            validity = new Date(now + this.tokenValidityInMillisecondsForRememberMe);
        } else {
            validity = new Date(now + this.tokenValidityInMilliseconds);
        }
        return Jwts.builder()
                .setSubject(authentication.getName())
                .claim(AUTHORITIES_KEY, authorities)
                .signWith(key, SignatureAlgorithm.HS512)
                .setExpiration(validity)
                .compact();
    }

    public String createTokenSocial(String email, Authority roll, boolean rememberMe){
        long now = (new Date()).getTime();
        Date validity;
        if (rememberMe) {
            validity = new Date(now + this.tokenValidityInMillisecondsForRememberMe);
        } else {
            validity = new Date(now + this.tokenValidityInMilliseconds);
        }
        return Jwts.builder()
                .setSubject(email)
                .claim(AUTHORITIES_KEY, roll.toString())
                .signWith(key, SignatureAlgorithm.HS512)
                .setExpiration(validity)
                .compact();
    }

    public Authentication getAuthentication(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(token)
                .getBody();

        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

        User principal = new User(claims.getSubject(), "", authorities);

        return new UsernamePasswordAuthenticationToken(principal, token, authorities);
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(key).parseClaimsJws(authToken);
            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            log.info("Invalid JWT signature.");
            log.trace("Invalid JWT signature trace: {}", e);
        } catch (ExpiredJwtException e) {
            log.info("Expired JWT token.");
            log.trace("Expired JWT token trace: {}", e);
        } catch (UnsupportedJwtException e) {
            log.info("Unsupported JWT token.");
            log.trace("Unsupported JWT token trace: {}", e);
        } catch (IllegalArgumentException e) {
            log.info("JWT token compact of handler are invalid.");
            log.trace("JWT token compact of handler are invalid trace: {}", e);
        }
        return false;
    }

    public Optional<taller.grado.proyectofinalbackend.model.User> getUser(String email) {
        return userRepository.findOneByEmailAndActivatedIsTrue(email);
    }



    public String refreshToken(JwtDTO jwtDto) throws ParseException {
        try {
            Jwts.parser().setSigningKey(key).parseClaimsJws(jwtDto.getToken());
        } catch (ExpiredJwtException e) {
            JWT jwt = JWTParser.parse(jwtDto.getToken());
            JWTClaimsSet claims = jwt.getJWTClaimsSet();
            String nombreUsuario = claims.getSubject();
            List<String> roles = (List<String>) claims.getClaim("roles");

            return Jwts.builder()
                    .setSubject(nombreUsuario)
                    .claim("roles", roles)
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(new Date().getTime() + 2000))
                    .signWith(SignatureAlgorithm.HS512, "secret.getBytes()")
                    .compact();
        }
        return null;
    }
}
