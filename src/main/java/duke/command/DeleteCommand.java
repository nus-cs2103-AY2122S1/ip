package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.Ui;

public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";
    public static final String USAGE_TEXT = "Usage: delete [task number]";

    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        try {
            // Delete task
            Task task = taskList.removeTask(index);
            ui.showTasksReply(false, "Aights! Pepper Jack deleted this task:\n\t" + task, taskList.size());
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Task number does not exist!\n\t" + DeleteCommand.USAGE_TEXT);
        }
    }
}
