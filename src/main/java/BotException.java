import java.io.IOException;

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

class InvalidTaskIndexException extends Exception{
    public InvalidTaskIndexException(String msg) {
        super(msg);
    }
}

class InvalidFileException extends IOException {
    public InvalidFileException (String msg) {
        super(msg);
    }
}

class InvalidDataFormatException extends ArrayIndexOutOfBoundsException {
    public InvalidDataFormatException (String msg) {
        super(msg);
    }
}