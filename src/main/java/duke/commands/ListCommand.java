package duke.commands;

import duke.DukeException;
import duke.Task;
import duke.TaskList;

/**
 * This class encapsulates a List Command.
 *
 * @author Kleon Ang
 */
public class ListCommand extends Command {
    public ListCommand(TaskList tasks) {
        super(tasks);
    }

    @Override
    public String getReply(String arguments) throws DukeException {
        return list(tasks, false);
    }

    /**
     * Lists the tasks in the given TaskList as a string.
     *
     * @param tasks TaskList containing the tasks to list.
     * @param withFind True if list is called from FindCommand, false otherwise.
     * @return A string representation of the given tasks.
     * @throws DukeException A Duke-specific exception that may occur when listing tasks.
     */
    public static String list(TaskList tasks, boolean withFind) throws DukeException {
        String matching = withFind ? "matching " : "";
        if (tasks.size() == 0) {
            throw new DukeException("There are currently no " + matching + "tasks in your list.");
        }
        StringBuilder tasksBuilder = new StringBuilder();
        tasksBuilder.append("Here are the ").append(matching).append("tasks in your list:\n");
        for (int i = 0; i < tasks.size(); ++i) {
            String counter = String.valueOf(i + 1);
            Task currentTask = tasks.get(i);
            tasksBuilder.append(counter).append(".").append(currentTask);
            if (i < tasks.size() - 1) {
                // Append newline for all tasks before last task
                tasksBuilder.append("\n");
            }
        }
        return tasksBuilder.toString();
    }
}
