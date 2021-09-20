package duke.commands;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.tasks.Task;

/**
 * Represents Command to edit a Task's description and/or date.
 */
public class EditCommand extends Command {
    private final int taskIndex;
    private final String description;
    private final String date;

    /**
     * Constructs an EditCommand to update the information of an existing Task in the TaskList.
     *
     * @param taskIndex Index of task to be edited.
     * @param description Edited description of task. May be null if description does not need to be updated.
     * @param date Edited description of task. May be null if date does not need to be updated.
     */
    public EditCommand(int taskIndex, String description, String date) {
        this.taskIndex = taskIndex;
        this.description = description;
        this.date = date;
    }

    /**
     * Returns description of updated task.
     *
     * @return Description of updated task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns date of updated task.
     *
     * @return Date of updated task.
     */
    public String getDate() {
        return date;
    }

    /**
     * Returns the task list with the updated task.
     *
     * @param tasks TaskList that contains task to be updated.
     * @param ui Ui that will display the updated task.
     * @param storage Storage where the TaskList should be saved.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task toEditTask = tasks.getTask(taskIndex);
        Task editedTask = toEditTask.getUpdatedTask(this);
        tasks.editTask(taskIndex, editedTask);
        Storage.saveTasks(tasks);
        return ui.showEditedTask(taskIndex, editedTask);
    }
}
