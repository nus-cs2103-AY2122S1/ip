package exception;

public class IncorrectFormatException extends DukeException {
    public IncorrectFormatException(String command, String missingWord) {
        super("Please use " + missingWord + " when you want to call the " + command + " command.");
    }
}
