package main.java.exception;

/**
 * The DescriptionException Exception is thrown when a Task is to be added but is missing description.
 * Thrown by Task and its child classes.
 * @author  Hoon Darren
 * @version 1.0
 * @since   2021-08-21
 */
public class DescriptionException extends DukeException {

    /**
     * Constructor to create a new DescriptionException.
     * @param task task which does not have a description.
     */
    public DescriptionException(String task) {
        super(String.format("The description of a %s cannot be empty.", task));
    }
}
