class InvalidCommandException extends Exception {
    public InvalidCommandException(String msg) {
        super(msg);
    }
}

class InvalidCommandFormatException extends Exception {
    public InvalidCommandFormatException(String msg) {
        super(msg);
    }
}

class EmptyTaskListException extends Exception {
    public EmptyTaskListException(String msg) {
        super(msg);
    }
}

class TaskOutOfRangeException extends Exception {
    public TaskOutOfRangeException(String msg) {
        super(msg);
    }
}