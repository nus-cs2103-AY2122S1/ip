public class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }
}

class UnknownCommandException extends DukeException {
    public UnknownCommandException() {
        super("Sorry but my database does not have such command.");
    }
}

class IllegalFormatException extends DukeException {
    public IllegalFormatException(String format) {
        super("Please follow this format:\n  " + format);
    }
}

class TaskNotFoundException extends DukeException {
    public TaskNotFoundException() {
        super("There is no such tasks with this task number!");
    }
}

class EmptyListException extends DukeException {
    public EmptyListException() {
        super("Your list is empty! Maybe add some tasks into it?");
    }
}
