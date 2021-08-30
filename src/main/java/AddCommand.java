public class AddCommand extends Command{
    private final Task task;

    public AddCommand(TaskList taskList, Storage storage, Ui ui, Task task) {
        super(taskList, storage, ui);
        this.task = task;
    }

    @Override
    public void runCommand(){
        taskList.addTask(task);
        ui.taskAddedMessage(task, taskList.size());
        storage.save(taskList.getList());
    }
}
