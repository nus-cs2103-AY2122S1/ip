import Exceptions.InvalidTaskNumberException;
import Tasks.Task;

public class DeleteTaskCommand extends Command {
    private String taskNumber;
    public DeleteTaskCommand(String taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws InvalidTaskNumberException {
        int index = Integer.parseInt(taskNumber);
        String[] messages = tasks.deleteTask(index);
        ui.printOut(messages);
        storage.save(tasks);
    }
}
