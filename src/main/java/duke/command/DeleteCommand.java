package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Class to delete a task from the current list of tasks.
 */
public class DeleteCommand implements Command {
    private String text;

    /**
     * Constructor to initialize DeleteCommand.
     *
     * @param s A string to be parsed to task number.
     */
    public DeleteCommand(String s) {
        this.text = s;
    }

    /**
     * Execute command.
     *
     * @param taskList TaskList that manages all current tasks.
     * @param ui Ui used to print messages.
     * @param storage Loads and saves the tasks to a txt file.
     * @throws DukeException Thrown if there are input/parsing errors.
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
     * Returns a boolean to determine if Duke should stop running.
     *
     * @return A boolean false as this is not an exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
