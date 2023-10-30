package taller.grado.proyectofinalbackend.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
public class ResetPasswordDTO {

    private Integer id;
    private String password;
    private String newPassword;
    private String confirmPassword;
}
