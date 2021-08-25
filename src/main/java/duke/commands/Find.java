package duke.commands;

import duke.data.TaskList;

import java.util.ArrayList;

/**
 * Encapsulates a Find command.
 * This class handles the searching of tasks based on user's input.
 *
 * @author: Jason Ng
 * @version: Duke Level-9
 */
public class Find {
    /**
     * Checks if description of task contains input string.
     *
     * @param input The input string.
     * @param description The description of a task to be checked against.
     * @return
     */
    public boolean isMatch(String input, String description) {
        return description.contains(input);
    }

    /**
     * Returns a list of tasks with descriptions that matches the input string.
     *
     * @param input The input string to be checked for matches with.
     * @param taskList The taskList to check against.
     * @return The new taskList with the matched tasks.
     */
    public TaskList findMatch(String input, TaskList taskList) {
        TaskList newList = new TaskList(new ArrayList<>());
        for (Task task : taskList.getEntire()) {
            if (isMatch(input, task.getDescription())) {
                newList.add(task);
            }
        }
        return newList;
    }
}
