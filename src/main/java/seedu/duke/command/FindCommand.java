package seedu.duke.command;

import seedu.duke.DukeException;
import seedu.duke.Ui;
import seedu.duke.task.TaskList;

/**
 * Represents a find command. A <code>FindCommand</code> describes
 * the action to be executed when a user indicates a keyword to search
 * for tasks with the matching keyword.
 */
public class FindCommand extends Command {
    private static final String FIND_MESSAGE = "Here are the matching tasks in your list.\n";
    private String keyword;

    /**
     * Public constructor for <code>FindCommand</code>.
     *
     * @param ui The Ui to handle user interactions.
     * @param taskList The task list to be updated.
     * @param keyword The keyword to search for among the tasks
     *                in the task list.
     */
    public FindCommand(Ui ui, TaskList taskList, String keyword) {
        super(ui, taskList);
        this.keyword = keyword;
    }

    /**
     * Returns the format on how to use the command.
     *
     * @return String representation of the help message.
     */
    @Override
    public String getUsageMessage() {
        return "find <keyword> | find tasks by the keyword";
    }

    /**
     * Finds matching tasks from the task list.
     */
    @Override
    public String execute() throws DukeException {
        TaskList matchingTasks = taskList.findTasksByKeyword(keyword);

        if (matchingTasks.isEmpty()) {
            throw new DukeException("No tasks matches the given keyword.");
        }

        return String.format("%s\n%s",
                FIND_MESSAGE, matchingTasks.toString());
    }

}

