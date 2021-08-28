package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * A Class that extends the Command class.
 * It is specifically designed for a Command for marking done.
 *
 * @author Gu Geng
 */
public class DoneCommand extends Command {
    private int index;

    /**
     * Returns a DoneCommand object with the information provided.
     *
     * @param command A String containing information that can possibility be used to create an DoneCommand object.
     * @throws duke.DukeException Will be thrown if information provided are insufficient/incorrect.
     */
    public DoneCommand(String command) throws DukeException {
        if (isDoneOps(command)) {
            index = Integer.parseInt(command.substring(5)) - 1;
        } else {
            throw new DukeException("☹ Would you specify the task for me my dear?");
        }
    }

    /**
     * Returns true if a valid done operation is entered.
     * False otherwise.
     *
     * @param input User input of the commands.
     * @return If the input contains a valid done operations.
     */
    public static boolean isDoneOps(String input) {
        int length = input.length();
        if (length < 6) {
            return false;
        }
        try {
            Integer.parseInt(input.substring(5));
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Implements the execute method from Command superclass.
     * Executes the given done command accordingly by updating taskList and storage, interacting with ui.
     *
     * @param taskList A duke.TaskList object that contains an ArrayList of duke.task.task object to be updated.
     * @param ui A duke.Ui object that helps to perform interaction when the command is executed.
     * @param storage A duke.Storage object that helps to update the storage after the execution is done.
     * @throws duke.DukeException Will be thrown if unable to locate/update the storage file.
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        if (index > taskList.size()) {
            throw new DukeException("☹ oopsie!!! The specified task does not exit.");
        }
        taskList.doneTask(index);
        ui.showDone(taskList.getTask(index));
        storage.updateStorage(taskList);
    }

    /**
     * Implements the isExit method from Command superclass.
     * Returns a boolean indicating if the programme terminates after the done execution.
     *
     * @return A boolean indicating if the programme terminates after the done execution.
     */
    public boolean isExit() {
        return false;
    }
}
