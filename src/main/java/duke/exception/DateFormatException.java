package duke.exception;

public class DateFormatException extends DukeException {
    @Override
    public String getMessage() {
        return "Duke cannot parse the date! Please ensure the date is in the format dd-MM-yyyy";
    }
}
