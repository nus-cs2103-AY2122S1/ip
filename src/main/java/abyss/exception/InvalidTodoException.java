package abyss.exception;

public class InvalidTodoException extends InvalidCommandException {
    public InvalidTodoException() {
        super("Description of a 'todo' task piece cannot be empty.");
    }
}
