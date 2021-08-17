class EmptyListException extends Exception {
    public EmptyListException() {
        super("Nee's list is empty! Add some tasks?");
    }
}

class UnknownCommandException extends Exception {
    public UnknownCommandException() {
        super("Nee doesn't understand your command!");
    }
}

class InvalidTaskException extends Exception {
    public InvalidTaskException() {
        super("Nee doesn't know what task you've done! \nAdd a description.");
    }
}

class TaskNotFoundException extends Exception {
    public TaskNotFoundException() {
        super("Invalid task number, please try again.");
    }
}
