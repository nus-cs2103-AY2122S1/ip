public class ExitCommand implements Command {
    public ExitCommand() {
        super();
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showExitMessage();
    };

    public boolean isExit() {
        return true;
    }
}
