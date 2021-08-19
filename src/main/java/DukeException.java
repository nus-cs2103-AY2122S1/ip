public class DukeException extends Exception {
    DukeException(String message) {
        super(message);
    }

    public static DukeException missingInput(String taskName) {
        return new DukeException(String.format("☹ OOPS!!! The description of a %s cannot be empty.", taskName));
    }
}
