package duke.exceptions;

public class DucInvalidFormatException extends DucException {
    public DucInvalidFormatException() {
        super("Please enter the date as YYYY-MM-DD!\n");
    }
}
