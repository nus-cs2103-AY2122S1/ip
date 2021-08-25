package duke.exception;

public class DukeException extends Exception {
    // raised based on what you enter
    // what you enter does not follow the format or abide by the duke.Duke rule
    public DukeException(String errorMessage) {
        super(errorMessage);
    }

    public DukeException() {
        super();
    }
}


