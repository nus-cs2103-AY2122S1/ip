package duke.command;

import java.util.ArrayList;

import duke.DukeException;
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
     * @param storage The storage for the tasks.
     * @return a string output.
     * @throws DukeException
     */
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        ArrayList<Task> matchingTasks = tasks.findTask(keyword);

        if (matchingTasks.size() == 0) {
            return "No matching tasks found for '" + keyword + "'. Try another keyword.";
        }
        ArrayList<String> taskStrings = new ArrayList<>();
        for (int i = 0; i < matchingTasks.size(); i++) {
            Task task = matchingTasks.get(i);
            int num = i + 1;
            taskStrings.add(num + "." + task.toString());
        }
        return formatOutput("I found these matching tasks in your list for '" + keyword + "':",
            formatOutput(taskStrings));
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
