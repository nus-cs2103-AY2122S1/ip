public class DukeException extends RuntimeException {
    public DukeException(String errorMessage) {
        super(errorMessage);
    }

    @Override
    public String getMessage() {
        return "OOPS!!! " + super.getMessage();
    }
}
