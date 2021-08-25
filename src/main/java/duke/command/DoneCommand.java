package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * This class is used to mark a task as completed
 */
public class DoneCommand implements  Command {
    private String number;

    /**
     *
     * @param no A string to be parsed to task number
     */
    public DoneCommand(String no) {
        this.number = no;
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
            int i = Integer.parseInt(number.trim()) - 1;
            String t = taskList.markDone(i);
            ui.echo("Nice! I marked this task as completed: " + t);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new DukeException("Oops! Enter a valid task no. to complete the task.");
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
