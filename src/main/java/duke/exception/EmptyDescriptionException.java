package duke.exception;

import java.lang.Exception;

/**
 * Represents exceptional scenario of when the user gives no
 * description for a todo task
 */
public class EmptyDescriptionException extends Exception{
    public EmptyDescriptionException(String exMsg) {
        super(exMsg);
    }
}
