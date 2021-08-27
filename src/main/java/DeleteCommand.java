public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Task task = taskList.get(this.index);
        taskList.deleteTask(this.index);
        ui.deleteTask(task);
    }
}
