package duke.commands;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class EditCommand extends Command {

    private int lineNumber;

    public EditCommand(CommandType commandType, int lineNumber) {
        super(commandType, false);
        this.lineNumber = lineNumber;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task editedTask = tasks.editTaskList(lineNumber, this.getCommandType());
        storage.editStorage(lineNumber, this.getCommandType());
        ui.showEditTaskMessage(editedTask, this.getCommandType(), tasks);
    }
}
