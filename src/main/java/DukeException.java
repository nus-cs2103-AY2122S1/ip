public class DukeException extends Exception {

    DukeException(String message) {
        super(message);
    }

    public String getMessage() {
        return "☹ OOPS!!! " + super.getMessage();
    }
}
