package duke.exceptions;

public class WrongDoneFormatException extends DukeException{
    public WrongDoneFormatException() {
        super("Done command requires a number!");
    }
}
