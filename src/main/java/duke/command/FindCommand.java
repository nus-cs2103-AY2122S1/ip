package duke.command;

import duke.ResponseLogic;
import duke.Storage;
import duke.task.TaskList;

/** The Command class responsible for finding tasks that contains a certain keyword. */
public class FindCommand extends Command {

    private String keyword;

    /**
     * Initializes the keyword query.
     *
     * @param keyword The keyword query.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String execute(TaskList tasks, ResponseLogic responseLogic, Storage storage) {
        return tasks.getTasksWithKeyword(keyword, responseLogic);
    }
}
