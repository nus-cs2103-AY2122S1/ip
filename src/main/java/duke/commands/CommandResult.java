package duke.commands;

public class CommandResult {
    private final String message;

    public CommandResult(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
