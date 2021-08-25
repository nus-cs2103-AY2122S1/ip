public class AddCommand extends Command{

    private Task task;

    public AddCommand(String input, Task task) {
        super(input);
        this.task = task;
    }

    @Override
    public boolean execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.addTask(task);
        ui.taskAddedMessage(task, taskList.getTotalNumberOfTask());
        return true;
    }


}
