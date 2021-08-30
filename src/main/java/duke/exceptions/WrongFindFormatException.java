package duke.exceptions;

public class WrongFindFormatException extends DukeException {
    public WrongFindFormatException() {
        super("Find command requires a description of what you want to find!");
    }

}
