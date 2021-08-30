public class DoneCommand extends Command{
    private final int taskNumber;

    public DoneCommand(TaskList taskList, Storage storage, Ui ui, int taskNumber) {
        super(taskList, storage, ui);
        this.taskNumber = taskNumber;
    }

    @Override
    public void runCommand() throws NoSuchTaskException {
        ui.taskDoneMessage(taskList.markAsDone(taskNumber));
        storage.save(taskList.getList());
    }
}
