package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * A class to handle print command to print tasks corresponding to a given date.
 */
public class PrintCommand extends Command {
    private String date;

    /**
     * Initializes an instance of PrintCommand class.
     * @param type Type of command
     * @param date The specified date
     */
    public PrintCommand(String type, String date) {
        super(type);
        this.date = date;
    }

    /**
     * Returns the specified date correspondign to which the tasks are to be printed
     * @return The specified date
     */
    public String getDate() {
        return date;
    }

    /**
     * Executes the command of printing tasks corresponding to the given date.
     * @param taskList The task list
     * @param ui An object to handle i/o operations through screen
     * @param storage An object to read from and write to the storage file
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            printTasksOnDate(taskList, ui);
        } catch (DukeException e) {
            ui.displayError(e.getMessage());
        }
    }

    /**
     * Prints tasks corresponding to the given date.
     * @param taskList The task list
     * @param ui An object to handle i/o operations through screen
     * @throws DukeException If no task on the given date is found
     */
    public void printTasksOnDate(TaskList taskList, Ui ui) throws DukeException {
        if (taskList.size() != 0) {
            String response = ui.getTasksOnDate(date, taskList);
            ui.displayResponse(response);
        } else {
            throw new DukeException("There are no tasks on this date!");
        }
    }
}
