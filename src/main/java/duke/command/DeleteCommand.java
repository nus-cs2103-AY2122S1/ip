package duke.command;

import duke.task.Task;
import duke.task.TaskList;

import duke.DukeException;
import duke.Storage;
import duke.Ui;

/**
 * Represents a command to delete a task
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";
    public static final String USAGE_TEXT = "Usage: delete [task number]";

    /** Index of task to be deleted */
    private int index;

    /**
     * DeleteCommand constructor.
     *
     * @param index Index of task to be deleted.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Deletes task at index from given TaskList.
     *
     * @param taskList List of tasks.
     * @param ui User interface.
     * @param storage Storage of tasks.
     * @throws DukeException If index is not valid.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        try {
            Task task = taskList.removeTask(index);
            return ui.showTasksReply(false,
                    "Aights! Pepper Jack deleted this task:\n\t" + task, taskList.size());
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Task number does not exist!\n\t" + DeleteCommand.USAGE_TEXT);
        }
    }
}
