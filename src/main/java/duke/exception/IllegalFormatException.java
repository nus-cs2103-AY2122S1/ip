package duke.exception;

import java.lang.Exception;

/**
 * Represents the exceptional scenario of when the user uses invalid
 * format when creating a task
 */
public class IllegalFormatException extends Exception{
    public IllegalFormatException(String exMsg) {
        super(exMsg);
    }
}