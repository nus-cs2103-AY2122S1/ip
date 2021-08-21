package duke.exception;

public class InvalidInputException extends DukeException {

    private String err;

    public InvalidInputException(String str) {
        super(str);
        this.err = str;
    }

    @Override
    public String toString() {
        return "Invalid input: " + err;
    }
}
