package duke.command;

import duke.data.TaskList;
import duke.data.exception.DukeException;
import duke.ui.Ui;

/**
 * Class that encapsulates the "Find" Command.
 *
 * @author Wang Hong Yong
 */
public class FindCommand extends Command {
    public static final String COMMAND_WORD = "FIND";
    public static final String COMMAND_USAGE = COMMAND_WORD
            + " : searches for tasks with similar keyword.";
    public static final String COMMAND_FORMAT = "Format: find <keyword> "
            + "(e.g find math)\n";
    private String input;

    /**
     * Constructor for FindCommand.
     *
     * @param tasklist Task Handler that handles the operation.
     * @param input command string to be executed.
     */
    public FindCommand(TaskList tasklist, String input) {
        super(tasklist);
        this.input = input;
    }

    /**
     * Executes the "Find" Command.
     *
     * @return string that represents details of searching up this task.
     * @throws DukeException if task faces an error during execution.
     */
    @Override
    public String execute() throws DukeException {
        int minCommandLength = 6;
        if (input.length() < minCommandLength) {
            throw new DukeException(Ui.getEmptyDescriptionMsg("find"));
        }
        String wordToFind = input.substring(minCommandLength - 1);
        return taskList.findTask(wordToFind);
    }
}
