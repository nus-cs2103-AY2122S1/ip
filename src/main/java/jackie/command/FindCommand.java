package jackie.command;

import jackie.JackieException;
import jackie.Storage;
import jackie.TaskList;
import jackie.Ui;

/**
 * A Class that extends the Command class.
 * It is specifically designed for a Command for filtering the list with provided text filter.
 *
 * @author Gu Geng
 */
public class FindCommand extends Command {
    private String searchFilter;

    /**
     * Returns a FindCommand object with the information provided.
     *
     * @param command A String containing information that can possibility be used to create an FindCommand object.
     * @throws jackie.JackieException Will be thrown if information provided are insufficient/incorrect.
     */
    public FindCommand(String... command) throws JackieException {
        if (command.length == 1) {
            // guard clause
            throw new jackie.JackieException(" D: OOPS!!! The description of a search cannot be empty.");
        } else {
            searchFilter = command[1];
        }
    }

    /**
     * Implements the execute method from Command superclass.
     * Executes the given finding/filtering command accordingly by updating taskList and storage, interacting with ui.
     * Returns a String of system reply when given certain input under execution.
     *
     * @param taskList A jackie.TaskList object that contains an ArrayList of jackie.task.task object to be updated.
     * @param ui A jackie.Ui object that helps to perform interaction when the command is executed.
     * @param storage A jackie.Storage object that helps to update the storage after the execution is done.
     * @return a String of system reply when given certain input under execution.
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        System.out.println(ui.showFindList(taskList, searchFilter));
        return ui.showFindList(taskList, searchFilter);
    }

    /**
     * Implements the isExit method from Command superclass.
     * Returns a boolean indicating if the programme terminates after the finding execution.
     *
     * @return A boolean indicating if the programme terminates after the finding execution.
     */
    public boolean isExit() {
        return false;
    }

}
