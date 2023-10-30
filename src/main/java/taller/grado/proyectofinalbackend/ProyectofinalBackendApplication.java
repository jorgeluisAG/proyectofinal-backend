package taller.grado.proyectofinalbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.core.env.Environment;


@SpringBootApplication
@ConfigurationPropertiesScan("taller.grado.proyectofinalbackend.config")
public class ProyectofinalBackendApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(ProyectofinalBackendApplication.class);
		Environment env = app.run(args).getEnvironment();
	}

}
