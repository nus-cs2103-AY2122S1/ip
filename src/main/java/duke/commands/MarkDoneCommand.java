package duke.commands;

import duke.exceptions.InvalidTaskNumberException;

import duke.utils.Storage;
import duke.utils.TaskList;
import duke.utils.Ui;

public class MarkDoneCommand extends Command {
    private int taskNumber;
    public MarkDoneCommand(String taskNumber) {
        this.taskNumber = Integer.parseInt(taskNumber);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws InvalidTaskNumberException {
        String[] messages = tasks.markDone(taskNumber);
        ui.printOut(messages);
        storage.save(tasks);
    }

    @Override
    public String toString() {
        return String.format("TO MARK DONE: index %d", taskNumber);
    }
}
