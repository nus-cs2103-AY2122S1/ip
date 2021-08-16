/**
 * This exception is thrown when no date or time for command is inputted.
 */
public class NoDateTimeInputException extends Exception {

    protected Duke.Command command;

    protected NoDateTimeInputException(Duke.Command command) {
        super(String.format("No date or time inputted for %s.", command));
        this.command = command;
    }
}
