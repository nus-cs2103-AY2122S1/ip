package duke.task.command;

import duke.DukeException;
import duke.TaskList;

/**
 * The command to find all tasks matching a given keyword.
 */
public class FindCommand extends Command {

    private TaskList taskList;
    private String keyword;

    /**
     * Instantiates a new class object.
     *
     * @param taskList the list of tasks.
     * @param keyword the word to be found.
     */
    public FindCommand(TaskList taskList, String keyword) {
        this.keyword = keyword;
        this.taskList = taskList;
    }

    /**
     * Executes the command.
     *
     * @return The output message of the command.
     * @throws DukeException Thrown when a duke exception happens.
     */
    @Override
    public String execute() throws DukeException {
        return taskList.findTask(keyword);
    }
}
