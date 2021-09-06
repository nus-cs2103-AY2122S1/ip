package duke.command;
import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.task.Find;
import duke.task.TaskList;

/**
 * Command to signal the system to search for given keyword.
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * Constructor for FindCommand.
     *
     * @param input The user input.
     */
    public FindCommand(String input) {
        this.keyword = input.substring(5);
    }

    /**
     * Searches for the keyword in the current tasks.
     *
     * @param ls Current TaskList.
     * @param ui Current Ui.
     * @param storage Current version of the saved tasks in the hard disk.
     * @throws DukeException If user input is invalid
     */
    @Override
    public void execute(TaskList ls, Ui ui, Storage storage) throws DukeException {
        Find f = new Find(keyword, ls);
        f.findWord();
    }

    /**
     * Signals to the system that the command is not an exit command.
     *
     * @return False.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
