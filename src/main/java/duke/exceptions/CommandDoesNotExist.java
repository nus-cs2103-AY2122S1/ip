package duke.exceptions;

public class CommandDoesNotExist extends DukeExceptions {
    public CommandDoesNotExist(String message) {
        super("Sorry! The command \"" + message + "\" doesn't exist :(\nPlease try again!");
    }
}
