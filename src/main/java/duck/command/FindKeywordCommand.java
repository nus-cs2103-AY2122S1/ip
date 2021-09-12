package duck.command;

import duck.TaskList;

/**
 * Represents the command that finds tasks on the task list that contains a specific keyword.
 */
public class FindKeywordCommand extends Command {
    private final String keyword;

    /**
     * Constructor for a FindKeywordCommand.
     *
     * @param keyword The keyword with which to find tasks.
     */
    public FindKeywordCommand(String keyword) {
        super();
        this.keyword = keyword;
    }

    /**
     * Executes the command of finding tasks containing the given keyword.
     *
     * @param taskList The TaskList used in the command.
     * @return String representing the list of tasks containing the given keyword.
     */
    @Override
    public String execute(TaskList taskList) {
        return taskList.findTasksByKeyword(keyword);
    }
}
