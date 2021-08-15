class UnknownCommandException extends Exception {
    public UnknownCommandException() {
        super("No such command, what a drag...");
    }
}

class WrongFormatException extends Exception {
    public WrongFormatException(String format) {
        super("Follow this format, don't make this worse:\n  " + format);
    }
}

class TaskIndexException extends Exception {
    public TaskIndexException() {
        super("No such task, more energy wasted...");
    }
}

class DeleteIndexException extends Exception {
    public DeleteIndexException() {
        super("Can't delete what isn't there...");
    }
}

class NoIndexException extends Exception {
    public NoIndexException() {
        super("Can't do anything without task number...");
    }
}

class TooManyIndexesException extends Exception {
    public TooManyIndexesException(String input) {
        super("I only have 1 hand, I can only " + input + " 1 at a time...");
    }
}
