package duke.command;

import duke.constant.TaskType;
import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;
import duke.ui.Ui;

/**
 * The AddCommand class encapsulates the data and instructions
 * needed to add a task to the user's task list on Duke.
 */
public class AddCommand extends Command {
    /** The type of task to be added. */
    private TaskType taskType;

    /** The description of the task to be added. */
    private String taskDescription;

    /** The date associated with the task to be added. */
    private String date;

    /**
     * Constructs an add command with information on the task to be added.
     *
     * @param taskType The type of task to be added.
     * @param taskDescription The description of the task to be added.
     * @param date The date associated with the task to be added.
     */
    public AddCommand(TaskType taskType, String taskDescription, String date) {
        this.taskType = taskType;
        this.taskDescription = taskDescription;
        this.date = date;
    }

    /**
     * Executes the instructions for adding a task to the user's task list on Duke.
     *
     * @param taskList Task list of the user loaded on Duke.
     * @param ui The object representing Duke's UI.
     * @param storage The object representing Duke's data and storage.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Task newTask;
        switch (this.taskType) {
        case DEADLINE:
            newTask = new Deadline(this.taskDescription, this.date);
            break;
        case EVENT:
            newTask = new Event(this.taskDescription, this.date);
            break;
        case TODO:
            newTask = new ToDo(this.taskDescription);
            break;
        default:
            throw new DukeException("Unknown command.");
        }
        taskList.add(newTask);
        storage.appendToSave(newTask);
        ui.updateUserOnAddedTask(newTask, taskList.getNumberOfTasks());
    }

    /**
     * Checks whether the command exits Duke.
     *
     * @return false.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Checks whether another object is equal with this add command.
     *
     * @param other The object being compared to.
     * @return true if both are add commands and share the same task type, description and date, false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        if (other instanceof AddCommand) {
            AddCommand otherCommand = (AddCommand) other;
            return this.taskType == otherCommand.taskType
                    && this.taskDescription.equals(otherCommand.taskDescription)
                    && this.date.equals(otherCommand.date);
        } else {
            return false;
        }
    }
}
