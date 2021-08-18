public class DukeException extends Exception {
    private static final String invalidInput = "OOPS!!! I'm sorry, but I don't know what that means :(";
    private static final String emptyDescription = "OOPS!!! The description of a task cannot be empty";

    private DukeException(String message) {
        super(message);
    }

    public static DukeException invalidInput() {
        return new DukeException(invalidInput);
    } 

    public static DukeException emptyDescription() {
        return new DukeException(emptyDescription);
    }
}
