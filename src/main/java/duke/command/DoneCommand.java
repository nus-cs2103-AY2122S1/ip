package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

import java.io.IOException;

/**
 * A class that contains methods to handle Done command
 */
public class DoneCommand extends Command {
    private int taskNum;

    /**
     * Initializes an instanceof Done class with task type and task number.
     * @param type Type of task
     * @param taskNum Task number
     */
    public DoneCommand(String type, int taskNum) {
        super(type);
        this.taskNum = taskNum;
    }

    /**
     * Executes the command of marking a task done, and updates the storage file.
     * @param taskList The task list
     * @param ui An object to handle i/o operations through screen
     * @param storage An object to read from and write to storage file
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            markDone(taskList, ui);
            storage.saveTasksToFile(taskList);
        } catch (DukeException | IOException e) {
            ui.displayError(e.getMessage());
        }
    }

    /**
     * Marks the task as done.
     * @param taskList The task list
     * @param ui An object to handle i/o operations through screen
     * @throws DukeException If the task number is not valid
     */
    public void markDone(TaskList taskList, Ui ui) throws DukeException {
        if (taskNum > 0 && taskNum <= taskList.size()) {
            Task taskDone = taskList.get(taskNum - 1);
            taskDone.setDone();

            String response = ui.taskDoneMessage(taskDone);
            ui.displayResponse(response);
        } else {
            throw new DukeException("Task not found. Please try again!");
        }
    }
}
