package duke.commands;

import java.io.IOException;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class SortCommand extends Command {
    /** The command word that identifies a SortCommand instance. */
    public static final String COMMAND_WORD = "sort";

    /** Guide on how to use this command. */
    public static final String MESSAGE_USAGE = COMMAND_WORD + " - sorts the task list in ascending date order.";

    /**
     * Returns true if the command is an exit command, return false otherwise.
     *
     * @return True if the command is an exit command, return false otherwise.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the respective command given and displays the result or error message(s).
     *
     * @param tasks TaskList that stores the list of tasks.
     * @param ui Ui instance that prints various messages.
     * @param storage Storage instance that reads and writes the task list.
     * @return Message to show whether successful execution of the command or error.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            String sortedList = tasks.sortList();
            storage.save(tasks.getItems());
            return sortedList;
        } catch (IOException e) {
            return ui.printError(e.getMessage());
        }
    }
}
