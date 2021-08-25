package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * This class deletes a task from the current list of tasks
 */
public class DeleteCommand implements  Command {
    private String text;

    /**
     *
     * @param s A string to be parsed to task number.
     */
    public DeleteCommand(String s) {
        this.text = s;
    }

    /**
     *
     * @param taskList Manages all current tasks
     * @param ui Used to print messages
     * @param storage Loads and saves the tasks to a txt file
     * @throws DukeException thrown if there are input/parsing errors
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        try {
            int i = Integer.parseInt(text.trim()) - 1;
            String t = taskList.removeTask(i);
            ui.echo("Noted. I removed this task: " + t);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new DukeException("Oops! Enter a valid task no. to delete the task.");
        }
    }

    /**
     * Method to determine if Duke should stop running.
     *
     * @return false as this is not an exit command
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
