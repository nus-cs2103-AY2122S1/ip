package duke.exception;

import java.lang.Exception;

/**
 * Represents the exceptional scenario of when the user's input
 * doesn't even resemble a format which duke can handle
 */
public class InvalidCommandException extends Exception{
    public InvalidCommandException(String exMsg) {
        super(exMsg);
    }
}