public class IncorrectCommand extends Command {
    String errorMessage;

    public IncorrectCommand(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showError(errorMessage);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
