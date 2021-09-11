package exception;

public class InvalidDateFormat extends DukeException {

    @Override
    public String getMessage() {
        return "Invalid format, please use the dd/mm/yyyy format for date.";
    }
}