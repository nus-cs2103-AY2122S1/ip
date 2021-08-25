package duke;

public class DukeException extends Exception {
    DukeException() {
        super("todo/event/delete cannot be empty\n");
    }

    DukeException(String message) {
        super("The description of a " + message + " cannot be empty.\n");
    }
}
