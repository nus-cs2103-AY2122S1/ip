package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;

/**
 * This FindCommand class represents a command to find tasks using a keyword.
 */
public class FindCommand extends Command {

    private String keyword;

    /**
     * Constructor for a FindCommand instance that takes in a keyword.
     *
     * @param keyword The keyword to search for.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Displays the matching tasks in the task list to the user.
     *
     * @param tasks The task list.
     * @param storage The storage system of the application.
     * @return Completion message of this command.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        TaskList tasksWithMatchingKeyword = tasks.findTasksWithKeyword(keyword);
        assert tasksWithMatchingKeyword != null : "A TaskList instance should be returned";
        return "Here are the matching tasks in your list:\n" + tasksWithMatchingKeyword.toString();
    }
}
