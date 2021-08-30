public class DeleteCommand extends Command{
    private final int taskNumber;

    public DeleteCommand(TaskList taskList, Storage storage, Ui ui, int taskNumber) {
        super(taskList, storage, ui);
        this.taskNumber = taskNumber;
    }

    @Override
    public void runCommand() throws NoSuchTaskException {
        Task deletedTask = taskList.deleteTask(taskNumber);
        ui.taskDeletedMessage(deletedTask, taskList.size());
        storage.save(taskList.getList());
    }
}

