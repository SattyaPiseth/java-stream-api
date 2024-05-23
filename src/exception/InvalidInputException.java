package exception;

import java.util.InputMismatchException;

/**
 * @author Sattya
 * create at 5/23/2024 6:41 PM
 */
public class InvalidInputException extends InputMismatchException {
    public InvalidInputException() {
        super();
    }
    public InvalidInputException(String message) {
        super(message);
    }
}
