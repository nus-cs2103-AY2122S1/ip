public class DeleteCommand extends Command {

    private final int taskNumber;

    DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    void execute(TaskList taskList, Ui ui, Storage storage) {
        Task removed = taskList.delete(taskNumber);
        ui.deletedMsg();
        ui.showTask(removed);
        ui.showListLength(taskList);
        storage.save(taskList);
    }

}
