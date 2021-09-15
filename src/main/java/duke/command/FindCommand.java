package duke.command;

import duke.DukeException;
import duke.TaskList;

/**
 * The command to add a find a task based on a keyword.
 */
public class FindCommand extends Command {

    private String keyword;
    private TaskList taskList;

    /**
     * The constructor for the FindCommand.
     * @param keyword The keyword to be searched.
     * @param taskList The list of tasks.
     */
    public FindCommand(String keyword, TaskList taskList) {
        this.taskList = taskList;
        this.keyword = keyword;
    }

    /**
     * Returns an output message after executing the find command.
     * @return The output message of the command.
     * @throws DukeException Thrown when a duke exception happens.
     */
    @Override
    public String execute() throws DukeException {
        return taskList.find(keyword);
    }
}
