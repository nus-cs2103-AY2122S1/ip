package duke.exception;

public class InvalidDateTimeException extends DukeException {
    public InvalidDateTimeException() {
        super("The format of your date is incorrect! It should be <yyyy-mm-dd HHmm>");
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
