package duke.command;
import duke.DukeException;
import duke.Input;
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
    public FindCommand(Input input) {
        this.keyword = input.getKeyword();
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
    public String execute(TaskList ls, Ui ui, Storage storage) throws DukeException {
        Find f = new Find(keyword, ls);
        return f.findWord();
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
