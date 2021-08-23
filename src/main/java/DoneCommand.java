public class DoneCommand extends Command {

    protected int taskNo;

    protected DoneCommand(int taskNo) {
        this.taskNo = taskNo;
    }

    @Override
    protected void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.markTaskAsDone(ui, this.taskNo);
        storage.updateTasks(taskList);
    }
}
