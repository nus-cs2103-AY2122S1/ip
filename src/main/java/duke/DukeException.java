package duke;

public class DukeException extends Exception {
    private static final String INVALID_INPUT = "OOPS!!! I'm sorry, but I don't know what that means :(";
    private static final String EMPTY_DESCRIPTION = "OOPS!!! The description of a todo cannot be empty";

    private DukeException(String message) {
        super(message);
    }

    public static DukeException invalidInput() {
        return new DukeException(INVALID_INPUT);
    }

    public static DukeException emptyDescription() {
        return new DukeException(EMPTY_DESCRIPTION);
    }
}
