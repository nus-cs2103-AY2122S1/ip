package duke.exception;

public class InvalidDateTimeException extends DukeException {

    public InvalidDateTimeException(String msg) {
        super(msg);
    }

    @Override
    public String getError() {
        String error = "OOPS!!! Please enter date & time in this format (yyyy-MM-dd HH:mm)";
        return error;
    }
}
