package duke.command;

import duke.DukeException;

/**
 * A Class that extends the Command class.
 * It is specifically designed for a Command for deleting.
 *
 * @author Gu Geng
 */
public class DeleteCommand extends Command {
    private int index;

    /**
     * Returns a DeleteCommand object with the information provided.
     *
     * @param command A String containing information that can possibility be used to create an DeleteCommand object.
     * @throws duke.DukeException Will be thrown if information provided are insufficient/incorrect.
     */
    public DeleteCommand(String command) throws DukeException {
        if (isDeleteOps(command)) {
            index = Integer.parseInt(command.substring(7)) - 1;
        } else {
            throw new DukeException("D: Would you specify the task for me my dear?");
        }
    }

    /**
     * Returns true if a valid delete operation is entered.
     * False otherwise.
     *
     * @param input User input of the commands.
     * @return If the input contains a valid delete operations.
     */
    public static boolean isDeleteOps(String input) {
        int length = input.length();
        if (length < 8) {
            return false;
        }
        try {
            Integer.parseInt(input.substring(7));
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Implements the execute method from Command superclass.
     * Executes the given delete command accordingly by updating taskList and storage, interacting with ui.
     * Returns a String of system reply when given certain input under execution.
     *
     * @param taskList A duke.TaskList object that contains an ArrayList of duke.task.task object to be updated.
     * @param ui A duke.Ui object that helps to perform interaction when the command is executed.
     * @param storage A duke.Storage object that helps to update the storage after the execution is done.
     * @return a String of system reply when given certain input under execution.
     * @throws duke.DukeException Will be thrown if unable to locate/update the storage file.
     */
    public String execute(duke.TaskList taskList, duke.Ui ui, duke.Storage storage) throws DukeException {
        if (index > taskList.size()) {
            throw new DukeException("D: oopsie!!! The specified task does not exit.");
        }
        duke.task.Task holder = taskList.getTask(index);
        taskList.deleteTask(index);
        System.out.println(ui.showDelete(holder, taskList.size()));
        storage.updateStorage(taskList);
        return ui.showDelete(holder, taskList.size());
    }

    /**
     * Implements the isExit method from Command superclass.
     * Returns a boolean indicating if the programme terminates after the delete execution.
     *
     * @return A boolean indicating if the programme terminates after the delete execution.
     */
    public boolean isExit() {
        return false;
    }
}
