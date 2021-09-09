package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

import java.io.IOException;

/**
 * A class that contaisn methods to delete a task
 */
public class DeleteCommand extends Command {
    private int taskNum;

    /**
     * Initializes an instance of DeleteCommand class with task type and task number.
     * @param type Task type
     * @param taskNum Task number
     */
    public DeleteCommand(String type, int taskNum) {
        super(type);
        this.taskNum = taskNum;
    }

    /**
     * Executes deleting the task from the task list, and updating the storage file.
     * @param taskList The task list
     * @param ui An object to handle i/o operations through screen
     * @param storage An object to read from and write to storage file
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            deleteTask(taskList, ui);
            storage.saveTasksToFile(taskList);
        } catch (DukeException | IOException e) {
            ui.displayError(e.getMessage());
        }
    }

    /**
     * Deletes a task from the task list.
     * @param taskList The task list
     * @param ui An object to handle i/o operations through screen
     * @throws DukeException If the task number is not valid
     */
    public void deleteTask(TaskList taskList, Ui ui) throws DukeException {
        if (taskNum > 0 && taskNum <= taskList.size()) {
            Task taskDeleted = taskList.remove(taskNum - 1);

            String response = ui.taskDeletedMessage(taskDeleted)
                    + System.lineSeparator() + ui.getNumOfTasksInList(taskList);
            ui.displayResponse(response);
        } else {
            throw new DukeException("Task not found. Please try again!");
        }
    }
}