package duke.commands;

import duke.utils.CliUi;
import duke.utils.Storage;
import duke.utils.TaskList;

/** Represents command to end Duke */
public class ByeCommand extends Command {

    private static final String BYE_MESSAGE = "Bye. Hope to see you again soon!";

    /**
     * Adds the task to the TaskList.
     *
     * @param taskList The list of tasks in Duke. Handles all task related functions.
     * @param cliUi Ui object to deal with user input/outputs.
     * @param storage Storage object to deal with saving taskList to disk.
     * @return String[] with the messages to be printed out to the Ui.
     */
    @Override
    public String execute(TaskList taskList, CliUi cliUi, Storage storage) {
        cliUi.printOut(BYE_MESSAGE);
        return BYE_MESSAGE;
    }

    /**
     * Checks if this command exits Duke.
     *
     * @return True.
     */
    @Override
    public boolean hasExited() {
        return true;
    }

    /**
     * Returns a String representation of the command.
     *
     * @return String representation of the command.
     */
    @Override
    public String toString() {
        return String.format("TO PRINT: %s", BYE_MESSAGE);
    }
}
