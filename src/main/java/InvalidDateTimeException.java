public class InvalidDateTimeException extends DukeException {
    public InvalidDateTimeException(Ui ui) {
        super(ui);
    }

    @Override
    public String getMessage() {
        return super.errorMessage("The format of Data/Time entered is invalid.\n " +
                "Do enter the Date/Time in yyyy-MM-dd HH:mm.");
    }
}
