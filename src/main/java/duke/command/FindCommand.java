package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * The FindCommand class encapsulates all data and instructions
 * needed to find all tasks that contain a keyword.
 */
public class FindCommand extends Command {
    private static final String NO_KEYWORD_ERROR_MESSAGE =
            "Please enter a keyword to search for in the following manner:\n"
            + "find <keyword>";
    /** The keyword to be matched in the find operation. */
    private String userInput;

    /**
     * Constructs a find command object with the necessary information to execute a find operation.
     *
     * @param userInput The user's input.
     */
    public FindCommand(String userInput) {
        this.userInput = userInput;
    }

    /**
     * Executes the instructions for a find operation.
     *
     * @param taskList The task list currently loaded on Duke.
     * @param ui The object representing the UI of Duke.
     * @param storage The object representing the storage of the Duke program.
     * @return A string to be displayed to the user on the user interface.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        String[] commandAndArgument = this.userInput.split(" ", 2);
        if (commandAndArgument.length < 2 || commandAndArgument[1].isBlank()) {
            throw new DukeException(NO_KEYWORD_ERROR_MESSAGE);
        }
        TaskList results = taskList.getMatchingTasks(commandAndArgument[1]);
        return ui.formatMatchingTasks(results);
    }
}
