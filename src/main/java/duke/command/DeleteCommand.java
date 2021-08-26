package duke.command;

import duke.exception.DukeException;
import duke.ui.Storage;
import duke.ui.TaskList;
import duke.ui.Ui;

import java.io.IOException;

/**
 * DeleteCommand class to handle the deletion of task from the list
 */
public class DeleteCommand extends Command {
    private final int index;


    /**
     * Constructor for the DeleteCommand
     *
     * @param input The index of the task to be deleted
     * @throws DukeException catches if the input is invalid
     */
    public DeleteCommand(String input) throws DukeException {
        super(true);
        if (input == null) {
            //Catch if there is no number after the command
            throw new DukeException("☹ OOPS!!! delete will require a task number to update.");
        }
        this.index = Integer.parseInt(input.trim());
    }

    /**
     * Executes the DeleteCommand to delete the task from the list,
     * update the respective list and txt file
     *
     * @param taskList The current list of tasks
     * @param ui       The current Ui
     * @param storage  The current storage class to handle the txt file
     * @throws IOException   if the filepath has any issues
     * @throws DukeException if there are any other format/input issues
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws IOException, DukeException {
        if (index > taskList.getCount() || index <= 0) {
            //Catches if the number is > than the number of task or if its negative
            throw new DukeException("☹ OOPS!!! The number is not in within the number of tasks!");
        } else {
            Ui.deleteMessage(taskList.delete(index - 1), taskList.getCount());
            Storage.updateText(taskList);
        }
    }
}
