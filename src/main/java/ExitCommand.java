public class ExitCommand extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.stop();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
