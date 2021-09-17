package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class FindTasksCommand extends Command {
    private final String keyword;

    public FindTasksCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Searches for and prints the tasks in the task list that contain the keyword.
     *
     * @param tasks TaskList that contains tasks to be searched through.
     * @param ui Ui that will display the tasks containing keyword.
     * @param storage Storage where the TaskList should be saved.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList tasksWithKeyword = tasks.findTasksWithKeyword(keyword);
        return tasksWithKeyword.printTaskListForFind();
    }

    /**
     * Returns true if object is a FindCommand with the same keyword.
     *
     * @param obj Object to be compared to FindCommand.
     * @return True if object is a FindCommand with the same keyword.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof FindTasksCommand) {
            FindTasksCommand otherCommand = (FindTasksCommand) obj;
            return keyword.equals(otherCommand.keyword);
        }
        return false;
    }
}
