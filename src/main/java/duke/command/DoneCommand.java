package duke.command;

import duke.Duke;
import duke.exception.DukeException;
import duke.ui.Storage;
import duke.ui.TaskList;
import duke.ui.Ui;

import java.io.IOException;

public class DoneCommand extends Command { //DoneCommand to handle the updating of list
    private final int index;

    /**
     * Constructor for the DoneCommand Class
     *
     * @param input index for the task that is to be marked as done
     * @throws DukeException Catches if the input is invalid
     */

    public DoneCommand(String input) throws DukeException {
        super(true);
        if (input == null) {
            //Catch if there is no number after the command
            throw new DukeException("☹ OOPS!!! done will require a task number to update.");
        }
        this.index = Integer.parseInt(input.trim());
    }

    /**
     * Executes the DoneCommand to update the task in the list as done.
     * Also updates the list and txt files accordingly.
     *
     * @param taskList The current list of tasks
     * @param ui       The current Ui
     * @param storage  The current storage class to handle the txt file
     * @throws IOException   If the filepath has issue
     * @throws DukeException catches if the input/format is wrong
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws IOException, DukeException {
        if (index > taskList.getCount() || index <= 0) {
            //Catches if the number is > than the number of task or if its negative
            throw new DukeException("☹ OOPS!!! The number is not in within the number of tasks!");
        } else {
            if (taskList.get(index - 1).isDone()) {
                throw new DukeException("☹ OOPS!!! That task has already been completed!");
            }
            Ui.doneMessage(taskList.get(index - 1).done());
            Storage.updateText(taskList);
        }
    }
}