package duke.commands;

import java.util.ArrayList;

import duke.data.TaskList;
/**
 * Encapsulates a Find command.
 * This class handles the searching of tasks based on user's input.
 *
 * @author Jason Ng
 * @version Duke Level-10
 */
public class Find {
    /**
     * Checks if description of task contains input string.
     *
     * @param input The input string.
     * @param description The description of a task to be checked against.
     * @return The boolean result of whether input matches a task description.
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
    public TaskList findMatch(TaskList taskList, String... input) {
        TaskList newList = new TaskList(new ArrayList<>());
        for (Task task : taskList.getEntire()) {
            // this way of searching ensures there is a match even if keyword matches the item only partially
            for (String word : input) {
                if (isMatch(word, task.getDescription())) {
                    newList.add(task);
                }
            }
        }
        return newList;
    }
}
