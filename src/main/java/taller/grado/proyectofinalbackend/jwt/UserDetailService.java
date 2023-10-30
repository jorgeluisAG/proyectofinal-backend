package taller.grado.proyectofinalbackend.jwt;

import taller.grado.proyectofinalbackend.model.User;
import taller.grado.proyectofinalbackend.repository.UserRepository;
import taller.grado.proyectofinalbackend.service.errors.UserError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

import static taller.grado.proyectofinalbackend.service.errors.ErrorConstants.USER_NOT_ACTIVATED;
import static taller.grado.proyectofinalbackend.service.errors.ErrorConstants.USER_NOT_FOUND;

@Component("userDetailsService")
public class UserDetailService implements UserDetailsService {

    private final Logger log = LoggerFactory.getLogger(UserDetailService.class);

    private final UserRepository userRepository;

    public UserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(final String login) {
        log.info("Authenticating {}", login);
        return userRepository.findOneByEmailAndActivatedIsTrue(login)
                .map(userSearched -> createSpringSecurityUser(login, userSearched))
                .orElseThrow(() -> new UserError(USER_NOT_FOUND));


    }

    private org.springframework.security.core.userdetails.User createSpringSecurityUser(String lowercaseLogin, User user) {
        if (!user.getActivated()) {
            throw new UserError(USER_NOT_ACTIVATED);
        }
        List<GrantedAuthority> grantedAuthorities = Collections.singletonList(new SimpleGrantedAuthority(user.getAuthority().getId()));
        System.out.println("CERRANDO Service");
        System.out.println(user.getEmail());
        return new org.springframework.security.core.userdetails.User(user.getEmail(),
                user.getPassword(),
                grantedAuthorities);
    }
}
