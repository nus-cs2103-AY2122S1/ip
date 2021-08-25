package duke.command;

import duke.util.TaskList;
import duke.util.Ui;
import duke.util.Storage;

import duke.exception.InvalidInputException;
import duke.exception.SaveFileException;
import duke.exception.TaskNotFoundException;
import duke.exception.TaskCompletedException;

import duke.task.Task;

/**
 * A command class encapsulating the logic that occurs when the user issues a 'bye' command.
 */
public class DoneCommand extends Command {
    /** The index of the todo to complete */
    String action;

    /**
     * Constructor for the Done Command
     * @param action The string input of the task
     */
    public DoneCommand(String action) {
        super(false);
        this.action = action;
    }

    /**
     * Sets a task to complete.
     *
     * @param tasks List of existing tasks
     * @param ui User interface current interacting with the user
     * @param storage Storage class handling the persistence of the tasks
     * @throws TaskNotFoundException if index of task is out of bounds
     * @throws InvalidInputException if input cannot be parsed into an integer
     * @throws TaskCompletedException if task is already completed
     * @throws SaveFileException if there are issues with the save file
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws TaskNotFoundException,
            InvalidInputException, TaskCompletedException, SaveFileException {
        try {
            int taskNumber = Integer.parseInt(action);
            if (taskNumber <= tasks.size() && taskNumber > 0) {
                Task taskToComplete = tasks.get(taskNumber - 1);
                if (!taskToComplete.isComplete()) {
                    taskToComplete.complete();
                    ui.showTaskDone(taskToComplete);
                }else {
                    throw new TaskCompletedException("This task is already completed.");
                }
            } else {
                throw new TaskNotFoundException("The task chosen does not exist. Use 'list' to see all your tasks.");
            }
            storage.save(tasks);
        } catch (NumberFormatException e) {
            throw new InvalidInputException("Command 'done' require an integer as the second parameter");
        }
    }
}
