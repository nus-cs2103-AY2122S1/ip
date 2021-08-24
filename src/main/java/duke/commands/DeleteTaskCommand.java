package duke.commands;

import duke.exceptions.InvalidTaskNumberException;
import duke.utils.Storage;
import duke.utils.TaskList;
import duke.utils.Ui;

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
