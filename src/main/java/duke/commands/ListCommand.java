package duke.commands;

import duke.utils.CliUi;
import duke.utils.Storage;
import duke.utils.TaskList;

/** Represents command to list all values in the current taskList */
public class ListCommand extends Command {

    /**
     * Lists all the tasks in the taskList.
     *
     * @param taskList The list of tasks in Duke. Handles all task related functions.
     * @param cliUi Ui object to deal with user input/outputs.
     * @param storage Storage object to deal with saving taskList to disk.
     * @return String[] with the messages to be printed out to the Ui.
     */
    @Override
    public String execute(TaskList taskList, CliUi cliUi, Storage storage) {
        String[] messages = taskList.showList(cliUi);
        return String.join("\n", messages);
    }

    /**
     * Returns a String representation of the command.
     *
     * @return String representation of the command.
     */
    @Override
    public String toString() {
        return "TO PRINT: Enumerated tasklist";
    }
}
