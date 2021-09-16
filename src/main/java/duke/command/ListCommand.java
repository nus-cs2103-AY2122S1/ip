package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * A class to handle the command of listing the tasks
 */
public class ListCommand extends Command {
    /**
     * Initializes an instance of ListCommand class.
     * @param type Type of task (List)
     */
    public ListCommand(String type) {
        super(type);
    }

    /**
     * Executes the command of listing the tasks.
     * @param taskList The task list
     * @param ui An object to handle i/o operations through screen
     * @param storage An object to read from and write to the storage file
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            displayTasksList(taskList, ui);
        } catch (DukeException e) {
            ui.displayError(e.getMessage());
        }
    }

    /**
     * Displays the tasks in proper format.
     * @param taskList The task list
     * @param ui An object to handle i/o operations through screen
     * @throws DukeException If the task list is empty
     */
    public void displayTasksList(TaskList taskList, Ui ui) throws DukeException {
        if (taskList.size() != 0) {
            String response = ui.getTasksInList(taskList);
            ui.displayResponse(response);
        } else {
            throw new DukeException("There are no tasks in your list!");
        }
    }
}
