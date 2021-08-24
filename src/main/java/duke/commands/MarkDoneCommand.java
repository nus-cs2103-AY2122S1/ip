package duke.commands;

import duke.exceptions.InvalidTaskNumberException;
import duke.utils.Storage;
import duke.utils.TaskList;
import duke.utils.Ui;

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
