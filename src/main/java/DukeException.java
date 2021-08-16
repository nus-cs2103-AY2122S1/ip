public class DukeException extends Exception {
    DukeException() {
        super("todo/event/delete cannot be empty");
    }

    DukeException(String message) {
        super("The description of a " + message + " cannot be empty.");
    }
}
