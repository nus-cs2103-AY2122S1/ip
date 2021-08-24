import Exceptions.DukeException;
import Exceptions.InvalidTaskNumberException;

public class MarkDoneCommand extends Command {
    private String taskNumber;
    public MarkDoneCommand(String taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws InvalidTaskNumberException {
        int index = Integer.parseInt(this.taskNumber);
        String[] messages = tasks.markDone(index);
        ui.printOut(messages);
        storage.save(tasks);
    }
}
