package duke.command;

import duke.task.Task;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Represents the FindCommand in the Duke program.
 */
public class FindCommand extends Command {
    private final String keyword;

    /**
     * Constructs a FindCommand to find all task(s) with the given keyword in the list of tasks.
     *
     * @param keyword Keyword to be used to find all task(s) in the list of tasks.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Returns the response after finding all task(s) with the given keyword.
     *
     * @param tasks Tasks of the Duke program.
     * @param ui Ui of the Duke program.
     * @param storage Storage of the Duke program.
     */
    @Override
    public String executeAndGetResponse(TaskList tasks, Ui ui, Storage storage) {
        TaskList matchingTasks = new TaskList();
        for (Task currTask : tasks.getTaskList()) {
            if (currTask.toString().contains(keyword)) {
                matchingTasks.addTask(currTask);
            }
        }
        String response = matchingTasks.getSize() == 0
                ? "There are no matching tasks."
                : "Here are the matching tasks in your list:\n" + matchingTasks;
        return response;
    }

    /**
     * Returns false as this command is not the ExitCommand.
     *
     * @return false as this command is not the ExitCommand.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
