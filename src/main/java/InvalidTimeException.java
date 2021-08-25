public class InvalidTimeException extends DukeException {
    public InvalidTimeException(String timeFormat) {
        super("☹ OOPS!!! The time is invalid.\nPlease input time in this form:\n" + timeFormat);
    }
}
