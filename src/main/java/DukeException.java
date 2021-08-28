class EmptyDescriptionException extends DukeException {
    public EmptyDescriptionException(String message) {
        super(message);
    }
    @Override
    public String toString() {
        return super.toString();
    }
}

class InvalidCommandException extends DukeException {
    public InvalidCommandException(String message) {
        super(message);
    }
    public String toString() {
        return super.toString();
    }
}

class InvalidDateTimeException extends DukeException {
    public InvalidDateTimeException(String message) {
        super(message);
    }
    public String toString() {
        return super.toString();
    }
}

public class DukeException extends Exception {
    private String message;
    public DukeException(String message) {
        this.message = message;
    }
    @Override
    public String toString() {
        return message;
    }
}