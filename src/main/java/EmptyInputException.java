public class EmptyInputException extends DukeException{
    public EmptyInputException(String message) {
        super("OOPS!!! The description of a " + message + " cannot be empty.");
    }
}
