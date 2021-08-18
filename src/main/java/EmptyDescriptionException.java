public class EmptyDescriptionException extends DukeException{

    public EmptyDescriptionException(String e) {
        super("Error: The description of that command cannot be empty. Please enter a description.");
    }
}
