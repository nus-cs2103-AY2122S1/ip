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
     * Executes the "Event" Command.
     * @return string that represents details of searching up this task.
     */
    @Override
    public String execute() throws DukeException {
        int minCommandLength = 4;
        if (input.length() == minCommandLength) {
            throw new DukeException(Ui.getEmptyDescriptionMsg("find"));
        }
        String wordToFind = input.substring(minCommandLength + 1);
        return taskList.findTask(wordToFind);
    }
}
