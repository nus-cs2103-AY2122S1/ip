package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;

/**
 * This class handles command that find a task by keywords.
 */
public class FindCommand extends Command {
    public static final String COMMAND_WORD = "find";
    public final String keyword;

    /**
     * Constructor for DoneCommand.
     * @param keyword Reference to the task done.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword.strip();
    }

    /**
     * Method to execute command.
     *
     * @param tasks The list of tasks in the current programme.
     * @param ui The user interface.
     * @param storage Handles interaction with the file.
     * @return A list of filtered tasks.
     * @throws DukeException All exceptions related to Duke.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        return tasks.filterByKeyword(keyword).display();
    }
}
