package taller.grado.proyectofinalbackend.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import taller.grado.proyectofinalbackend.config.ApplicationProperties;
import taller.grado.proyectofinalbackend.controller.errors.BadRequestAlertException;
import taller.grado.proyectofinalbackend.jwt.JWTFilter;
import taller.grado.proyectofinalbackend.jwt.JwtProvider;
import taller.grado.proyectofinalbackend.jwt.LoginVM;
import taller.grado.proyectofinalbackend.model.User;
import taller.grado.proyectofinalbackend.model.dto.JwtDTO;
import taller.grado.proyectofinalbackend.service.UserService;
import taller.grado.proyectofinalbackend.service.errors.UserError;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.text.ParseException;
import java.util.Optional;

import static taller.grado.proyectofinalbackend.service.errors.ErrorConstants.USER_NOT_FOUND;

@RestController
@RequestMapping("v1/api")
public class JWTController {

    private final JwtProvider jwtProvider;

    private final AuthenticationManager authenticationManager;

    private final UserService userService;


    private final ApplicationProperties applicationProperties;


    public JWTController(JwtProvider jwtProvider, AuthenticationManager authenticationManager, UserService userService,
                         ApplicationProperties applicationProperties) {
        this.jwtProvider = jwtProvider;
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.applicationProperties = applicationProperties;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<JWTToken> authorize(@Valid @RequestBody LoginVM loginVM) {
        try {

            Optional<User> userEmail = userService.getByEmail(loginVM.getEmail());
            if(!userEmail.isPresent()){
                userEmail = userService.getByUserName(loginVM.getEmail());
            }
            userEmail.ifPresent(user -> loginVM.setEmail(user.getEmail()));
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(loginVM.getEmail(), loginVM.getPassword());
            Authentication authentication = this.authenticationManager.authenticate(authenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            boolean rememberMe = loginVM.isRememberMe() != null && loginVM.isRememberMe();
            String jwt = jwtProvider.createToken(authentication, rememberMe);
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add(JWTFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);
            User user = null;
            if ( jwtProvider.getUser(loginVM.getEmail()).isPresent() ) {
                user = jwtProvider.getUser(loginVM.getEmail()).get();
                user.setPassword(":)");
                //user.setDocumentType(":)");
                //user.setDocumentNumber(":)");
                //user.setPhone(":)");
                user.setCreatedAt(null);
            }
            return new ResponseEntity<>(new JWTToken(jwt, user), httpHeaders, HttpStatus.OK);
        } catch (UserError e) {
            throw new BadRequestAlertException(e.getTitle(), "User", USER_NOT_FOUND);
        }

    }


    @PostMapping("/refresh")
    public ResponseEntity<JWTToken> refresh(@RequestBody JwtDTO jwtDto) throws ParseException {
        String token = jwtProvider.refreshToken(jwtDto);
        JwtDTO jwt = new JwtDTO(token);
        return new ResponseEntity(jwt, HttpStatus.OK);
    }

    @GetMapping("/verification")
    public ResponseEntity<?> verification(HttpServletRequest request) throws ParseException {
        return new ResponseEntity<>(request.getHeader("Authorization").split(" ")[1], HttpStatus.OK);
        //return new ResponseEntity(request.getHeader("Authorization").split(" ")[1], HttpStatus.OK);
    }

    static class JWTToken {

        private String idToken;
        private User user;

        JWTToken(String idToken, User user) {
            this.idToken = idToken;
            this.user = user;
        }

        @JsonProperty("id_token")
        String getIdToken() {
            return idToken;
        }

        @JsonProperty("user")
        User getUser() {
            return user;
        }

        void setIdToken(String idToken) {
            this.idToken = idToken;
        }
    }
}
