package duke.exceptions;


public class DukeException extends RuntimeException {

    public DukeException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return new StringBuilder()
                .append("Error: ")
                .append(super.getMessage())
                .toString();
    }
}
