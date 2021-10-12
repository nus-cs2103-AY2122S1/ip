package jackie.command;

import jackie.JackieException;

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
     * @param command A String array containing information
     *                that can possibly be used to create an DeleteCommand object.
     * @throws jackie.JackieException Will be thrown if information provided are insufficient/incorrect.
     */
    public DeleteCommand(String... command) throws JackieException {
        if (isDeleteOps(command)) {
            index = Integer.parseInt(command[1]) - 1;
        } else {
            throw new JackieException("D: Would you specify the task for me my dear?");
        }
    }

    /**
     * Returns true if a valid delete operation is entered.
     * False otherwise.
     *
     * @param input User input of the commands.
     * @return If the input contains a valid delete operations.
     */
    public static boolean isDeleteOps(String... input) {
        int length = input.length;
        if (length < 2) {
            // guard clause
            return false;
        }
        try {
            Integer.parseInt(input[1]);
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
     * @param taskList A jackie.TaskList object that contains an ArrayList of jackie.task.task object to be updated.
     * @param ui A jackie.Ui object that helps to perform interaction when the command is executed.
     * @param storage A jackie.Storage object that helps to update the storage after the execution is done.
     * @return a String of system reply when given certain input under execution.
     * @throws jackie.JackieException Will be thrown if unable to locate/update the storage file.
     */
    public String execute(jackie.TaskList taskList, jackie.Ui ui, jackie.Storage storage) throws JackieException {
        if (index > taskList.size() || taskList.size() == 0) {
            throw new JackieException("D: oopsie!!! The specified task does not exit.");
        }
        jackie.task.Task holder = taskList.getTask(index);
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
