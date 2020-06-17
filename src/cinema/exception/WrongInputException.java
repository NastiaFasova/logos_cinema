package cinema.exception;

public class WrongInputException extends RuntimeException {
    public WrongInputException(String message, Throwable cause) {
        super(message, cause);
    }
}
