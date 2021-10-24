package duke;

/**
 * Command to close the application.
 */
public class ErrorCommand extends Command {

    private final String errorMessage;

    ErrorCommand(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage, Statistics stats) throws DukeException {
        return ui.printErrorMessage(errorMessage);
    }

    @Override
    public boolean isClosed() {
        return true;
    }
}
