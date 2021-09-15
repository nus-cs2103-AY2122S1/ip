package duke.exception;

/**
 * Represents a duke exception when an unknown command is provided.
 */
public class DukeUnknownCommandException extends DukeException {
    private String command;

    /**
     * Constructor for DukeUnknownCommandException.
     *
     * @param command the unknown command
     */
    public DukeUnknownCommandException(String command) {
        this.command = command;
    }


    /**
     * Obtain the string representation of the exception.
     *
     * @return string representation of the exception
     */
    @Override
    public String toString() {
        return String.format("%s I do not know how to handle the command '%s'!",
                super.toString(),
                this.command
        );
    }
}
