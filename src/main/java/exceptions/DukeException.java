package exceptions;

public class DukeException extends Exception {
    // raised based on what you enter
    // what you enter does not follow the format or abide by the Duke rule
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}


