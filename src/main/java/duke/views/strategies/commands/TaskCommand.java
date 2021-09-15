package duke.views.strategies.commands;

import duke.domain.Task;
import duke.domain.TaskList;

/**
 * Encapsulates a command involving the task list.
 */
public abstract class TaskCommand extends Command {
    protected final TaskList userTasks;

    public TaskCommand(TaskList userTasks) {
        this.userTasks = userTasks;
    }

    protected String stringifyTasks(TaskList tasks) {
        String result = "";
        for (int i = 0; i < tasks.size(); i++) {
            result += String.format("%d. %s%s", (i + 1), tasks.get(i), System.lineSeparator());
        }
        return result;
    }

    protected String listTasksWithMessage(String message, TaskList tasks, String emptyMessage) {
        if (tasks.size() == 0) {
            return emptyMessage + System.lineSeparator();
        }
        String result = message + System.lineSeparator();

        result += stringifyTasks(tasks);
        return result;
    }

    protected String formatTaskCount() {
        String s = "";
        if (userTasks.size() != 1) {
            s = "s";
        }
        return String.format("Now you have %d task%s in the list.", userTasks.size(), s);
    }

    protected String formatAdd(Task t) {
        return String.format("Got it. I've added this task:%s%s%s%s%s", System.lineSeparator(), t,
                System.lineSeparator(), formatTaskCount(), System.lineSeparator());
    }

    protected String getDuplicateMessage() {
        return "Note: You have attempted to add a duplicate task. It will not be added again." + System.lineSeparator();
    }
}
