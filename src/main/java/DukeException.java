public class DukeException extends Exception {
    DukeException(String err) {
        super(err);
    }
}

class EmptyListException extends DukeException {
    public EmptyListException() {
        super("Nee's list is empty! Add some tasks?");
    }
}

class UnknownCommandException extends DukeException {
    public UnknownCommandException() {
        super("Nee doesn't understand your command!");
    }
}

class InvalidTaskException extends DukeException {
    public InvalidTaskException() {
        super("Nee doesn't know what task you've done! \nAdd a description.");
    }
}

class TaskNotFoundException extends DukeException {
    public TaskNotFoundException() {
        super("Invalid task number, please try again.");
    }
}
