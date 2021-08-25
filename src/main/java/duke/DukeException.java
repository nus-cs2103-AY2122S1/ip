package duke;

public class DukeException extends Exception {
    DukeException() {
        super("todo/event/delete cannot be empty.\n");
    }

    DukeException(String message, String desc) {
        super("The description of a " + message + " cannot be empty. It has to be in this format: " + desc + "\n");
    }
}
