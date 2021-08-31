package duke;

public class ExitCommand implements Command {
    public ExitCommand() {
        super();
    }

    public String getResponse(TaskList tasks, Ui ui, Storage storage) {
        return ui.showExitMessage();
    };

    public boolean isExit() {
        return true;
    }
}
