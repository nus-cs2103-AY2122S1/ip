package duke.command;

import java.util.ArrayList;

import duke.DukeException;
import duke.storage.TaskList;
import duke.task.Task;

/**
 * Command to find tasks based on a keyword
 */
public class FindCommand implements Command {
    private String keyword;

    /**
     * Constructor for <code>FindCommand</code>
     *
     * @param keyword keyword to find tasks
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Runs the command
     *
     * @return the tasks found based on the keyword
     * @throws DukeException if cannot access memory
     */
    @Override
    public String run() throws DukeException {
        ArrayList<Task> foundTasks = TaskList.getInstance().find(keyword);
        if (foundTasks.size() <= 0) {
            return "No matching task found!";
        }
        String message = "Here are the matching tasks in your list:\n";
        for (int i = 0; i < foundTasks.size(); i++) {
            message += String.format("\t\t%d. %s\n", i + 1, foundTasks.get(i).toString());
        }
        return message;
    }
}
