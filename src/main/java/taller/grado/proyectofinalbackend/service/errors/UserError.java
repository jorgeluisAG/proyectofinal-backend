package taller.grado.proyectofinalbackend.service.errors;

import org.zalando.problem.AbstractThrowableProblem;

public class UserError extends AbstractThrowableProblem {

    public UserError(String message) {
        super(null, message);
    }
}
