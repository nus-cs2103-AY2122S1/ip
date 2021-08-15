public class DukeException extends Exception {
    public DukeException(String message) {
        super("☹ OOPS!!! " + message);
    }
}

class EmptyTodoDescriptionException extends DukeException {
    public EmptyTodoDescriptionException() {
        super("The description of a todo cannot be empty.");
    }
}

class UnknownCommandException extends DukeException {
    public UnknownCommandException() {
        super("I'm sorry, but I don't know what that means :-(");
    }
}

class InvalidFormatException extends DukeException {
    public InvalidFormatException(String format) {
        super("Please follow this format: " + format);
    }
}

class InvalidTaskIndexException extends DukeException {
    public InvalidTaskIndexException() {
        super("Invalid task index!");
    }
}