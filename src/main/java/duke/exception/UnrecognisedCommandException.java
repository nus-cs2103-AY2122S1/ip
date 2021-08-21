package duke.exception;

public class UnrecognisedCommandException extends DukeException {

    public UnrecognisedCommandException() {
        super("This command is not recognised! Please enter a valid command!");
    }
}
