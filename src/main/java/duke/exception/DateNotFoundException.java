package duke.exception;

public class DateNotFoundException extends DukeException {
    public DateNotFoundException() {
        super("Date not indicated! Please try again");
    }
}
