public class EmptyDescriptionException extends DukeException {

    private final String command;
    public EmptyDescriptionException(String command) {
        super("");
        this.command = command;
    }

    @Override
    public String getMessage() {
        return String.format("☹ OOPS!!! The description of %s cannot be empty.", this.command);
    }
}
