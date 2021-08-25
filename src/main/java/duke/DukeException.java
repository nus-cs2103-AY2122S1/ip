package duke;

/**
 * Exception for the duke.
 */
public class DukeException extends Exception {
    /**
     * Constructs DukeExcpetion with message.
     * @param description the message of the DukeException.
     */
    public DukeException(String description) {
        super(description);
    }

    /**
     * Gets the string representation of the duke exception.
     *
     * @return the representation of the duke exception.
     */
    @Override
    public String toString() {
        String strBreak = "    ____________________________________________________________";
        return String.format("%s\n    %s\n%s", strBreak, super.getMessage(), strBreak);
    }
}
