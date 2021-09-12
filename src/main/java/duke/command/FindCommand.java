package duke.command;
import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Class to encapsulate a FindCommand
 */
public class FindCommand extends Command {
    public static final String INSTRUCTION_FIND = "find";
    protected String description;

    /**
     * Class constructor for FindCommand Class specifying the task number
     */
    public FindCommand(String description) throws DukeException {
        if (description.equals("")) {
            throw new DukeException("OOPS!!! The task number of delete cannot be empty.");
        }
        this.description = description;
    }

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
        TaskList foundTasks = new TaskList(tasks.match(description));
        return ui.getList(foundTasks);
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
        return "[" + INSTRUCTION_FIND + "] - " + description;
    }
}
