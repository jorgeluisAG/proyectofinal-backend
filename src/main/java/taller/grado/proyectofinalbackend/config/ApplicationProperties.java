package taller.grado.proyectofinalbackend.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter @Setter
@ConfigurationProperties(prefix = "application", ignoreUnknownFields = false)
public class ApplicationProperties {

    private Jwt jwt;

    @Getter @Setter
    public static class Jwt {
        private String base64Secret;
        private Long tokenValidityInSeconds;
        private Long tokenValidityInSecondsForRememberMe;
    }

}
