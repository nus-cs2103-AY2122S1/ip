package main.java;

public class DescriptionException extends DukeException {

    /**
     * Constructor to create a new DescriptionException
     * @param task the task who does not have a description
     */
    public DescriptionException(String task) {
        super(String.format("The description of a %s cannot be empty.", task));
    }
}
