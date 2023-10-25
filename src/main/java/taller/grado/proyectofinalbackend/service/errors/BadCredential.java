package taller.grado.proyectofinalbackend.service.errors;

public class BadCredential extends Exception {
    public BadCredential() {
        super("Bad Credentials");
    }

    public BadCredential(String message) {
        super(message);
    }
}
