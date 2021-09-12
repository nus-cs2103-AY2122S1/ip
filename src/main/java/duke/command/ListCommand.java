package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Class to encapsulate a ListCommand
 */
public class ListCommand extends Command {
    public static final String INSTRUCTION_LIST = "list";

    /**
     * Executes the command
     *
     * @param tasks    the TaskList
     * @param ui       the Ui
     * @param storage  the data source
     *
     * @return         string stating the command result
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.getList(tasks);
    }

    /**
     * Checks if the command is an ExitCommand
     *
     * @return           boolean stating if command is ExitCommand
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Returns the toString of the class
     *
     * @return           toString of the class
     */
    @Override
    public String toString() {
        return "[" + INSTRUCTION_LIST + "]";
    }
}
