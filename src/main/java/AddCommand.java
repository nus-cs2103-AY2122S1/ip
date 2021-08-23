public class AddCommand extends Command {

    protected Task task;

    protected AddCommand(Task task) {
        this.task = task;
    }

    @Override
    protected void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.addTask(ui, this.task);
        storage.updateTasks(taskList);
    }
}
