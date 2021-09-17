package duke.command;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Class to encapsulate a ExitCommand
 */
public class ExitCommand extends Command {
    public static final String INSTRUCTION_EXIT = "bye";

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
        return "See ya round, buckaroo.";
    }

    /**
     * Checks if the command is an ExitCommand
     *
     * @return           boolean stating if command is ExitCommand
     */
    @Override
    public boolean isExit() {
        return true;
    }

    /**
     * Returns the toString of the class
     *
     * @return           toString of the class
     */
    @Override
    public String toString() {
        return "[" + INSTRUCTION_EXIT + "]";
    }
}
