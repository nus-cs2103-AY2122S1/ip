package duke.exceptions;

public class WrongTimeFormatException extends DukeException {
    public WrongTimeFormatException() {
        super("Time format needs to be of the form HH:mm!");
    }
}
