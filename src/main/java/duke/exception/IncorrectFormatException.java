package duke.exception;

public class IncorrectFormatException extends DukeException {
    public IncorrectFormatException(String command, String missingWord) {
        super("Please use " + missingWord + " when you want to call the "
                + command
                + " command and include the date and time as YYYY/MM/DD and HHMM in 24 hour format.");
    }
}
