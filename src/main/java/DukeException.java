public class DukeException extends Exception {
    public DukeException(String errorMessage) {
        super(errorMessage);
    }

    public DukeException(String errorMessage, Throwable error) {
        super(errorMessage, error);
    }

    @Override
    public String toString() {
        return "OOPS!! " + this.getMessage();
    }
}
