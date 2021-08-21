public class DeleteCommand extends Command {
    private TaskList taskList;
    private int index;

    public DeleteCommand(TaskList taskList, int index) {
        this.taskList = taskList;
        this.index = index;
    }

    @Override
    public void execute() {
        Ui.displayMessage(this.taskList.deleteTask(this.index));
    }
}