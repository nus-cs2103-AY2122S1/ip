package duke.exceptions;

/** DukeException representing unknown commands */
public class InvalidCommandDukeException extends DukeException {
    public InvalidCommandDukeException() {
        super("Command not recognized");
    }
}
