/**
 * Represents the specific duke exception that is related to empty user input.
 *
 * @author QIN GUORUI
 */
public class EmptyInputException extends DukeException{
    public EmptyInputException(String message) {
        super("OOPS!!! The description of a " + message + " cannot be empty.");
    }
}
