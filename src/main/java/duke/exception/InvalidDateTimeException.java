package duke.exception;

public class InvalidDateTimeException extends DukeException {
    String error;

    public InvalidDateTimeException(String msg) {
        super(msg);
        this.error = "OOPS!!! Please enter date & time in this format (yyyy-MM-dd HH:mm)";
    }

    @Override
    public void printError() {
        System.out.println(this.error);
    }

    @Override
    public String getError() {
        return this.error;
    }
}
