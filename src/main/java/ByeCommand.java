public class ByeCommand extends Command {
    @Override
    public TaskList execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showMessage("Goodbye. Have a nice day!");
        return tasks;
    }

    @Override
    public boolean isTerminated() {
        return true;
    }
}
