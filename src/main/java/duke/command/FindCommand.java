package duke.command;

import java.util.ArrayList;

import duke.DukeException;
import duke.Ui;
import duke.task.Storage;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Represents a Command that Finds a task.
 */
public class FindCommand extends Command {
    /**
     * The keyword to find.
     */
    private final String keyword;

    /**
     * Constructs a Find command.
     *
     * @param keyword The keyword to find.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the Find command.
     *
     * @param tasks   The task list to execute the command on.
     * @param ui      The user interface.
     * @param storage The storage for the tasks.
     * @throws DukeException
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ArrayList<Task> matchingTasks = tasks.findTask(keyword);

        if (matchingTasks.size() == 0) {
            ui.showMessage("No matching tasks found for '" + keyword + "'. Try another keyword.");
            return;
        }
        ui.showMessage("I found these matching tasks in your list for '" + keyword + "':");
        for (int i = 0; i < matchingTasks.size(); i++) {
            Task task = matchingTasks.get(i);
            int num = i + 1;
            ui.showMessage((num + "." + task.toString()));
        }
    }

    /**
     * Returns false to continue the program.
     *
     * @return false.
     */
    public boolean isExit() {
        return false;
    }
}
