package duke;

/**
 * A class that handles exiting the chat box.
 */
public class ExitCommand implements Command {

    /**
     * Constructor for ExitCommand object.
     */
    public ExitCommand() {
        super();
    }

    @Override
    public String getResponse(TaskList tasks, Ui ui, Storage storage) {
        return ui.getExitMessage();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
