package duke.command;

import duke.data.Storage;
import duke.exceptions.InvalidInputException;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Message;
import duke.ui.Ui;

/**
 * DoneCommand represents a command to mark a task as completed.
 */
public class DoneCommand extends Command {
    /** Attribute for a DoneCommand object */
    private int taskNumber;

    /**
     * Initialises a DoneCommand object.
     *
     * @param taskNumber the task number of the task to be marked done
     */
    public DoneCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Marks the task as done and returns the marked task.
     *
     * @param tasks the current task list
     * @return the marked task
     * @throws InvalidInputException if the task number entered is invalid
     */
    private Task markTaskAsDone(TaskList tasks) throws InvalidInputException {
        int taskPosition = taskNumber - 1;
        if (taskPosition >= tasks.size()) {
            throw new InvalidInputException(Message.MESSAGE_INVALID_TASK_NUMBER);
        } else {
            Task taskMarked = tasks.getTask(taskPosition);
            taskMarked.markAsDone();
            return taskMarked;
        }
    }

    /**
     * Executes the DoneCommand object.
     *
     * @param taskList the current task list
     * @param ui the ui object used
     * @param storage the current storage
     * @return a string message representing the marked task
     * @throws InvalidInputException if the task number provided is invalid
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws InvalidInputException {
        Task markedTask = markTaskAsDone(taskList);
        storage.update(taskList);
        return ui.showMarkTaskMessage(markedTask);
    }
}
