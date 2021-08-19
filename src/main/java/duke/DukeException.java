package duke;

/**
 * This exception is currently used only when the application recognises the command, but the necessary description
 * is empty, or if the saved data has an improper format.
 */
public class DukeException extends Exception{
    public DukeException(String description) {
        super(description);
    }
}
