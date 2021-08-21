package duke.data.exception;

public class InvalidTimeException extends DukeException {
    @Override
    public String toString() {
        return "OOPS!!! Please enter a date & time in the following format: DD/MM/YYYY hhmm (eg. 22/06/2000 1800)";
    }
}
