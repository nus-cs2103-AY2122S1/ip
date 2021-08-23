public class DeleteCommand extends Command {

    protected int taskNo;

    protected DeleteCommand(int taskNo) {
        this.taskNo = taskNo;
    }

    @Override
    protected void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.deleteTask(ui, this.taskNo);
        storage.updateTasks(taskList);
    }
}
