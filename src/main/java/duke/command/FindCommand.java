package duke.command;

import duke.DukeException;
import duke.TaskList;

/**
 * The command to add a find a task based on a keyword.
 */
public class FindCommand extends Command {

    private String keyword;
    private TaskList taskList;

    public FindCommand(String keyword, TaskList taskList) {
        this.taskList = taskList;
        this.keyword = keyword;
    }

    @Override
    public String execute() throws DukeException {
        return taskList.find(keyword);
    }
}
