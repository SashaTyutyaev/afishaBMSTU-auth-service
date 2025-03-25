package afishaBMSTU.auth_service.exception;

public class IncorrectTokenException extends RuntimeException {

    public IncorrectTokenException(String message) {
        super(message);
    }

}
