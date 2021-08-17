public class DukeException extends RuntimeException{
    public DukeException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return "☹ OOPS!!! " + super.getMessage();
    }
}
