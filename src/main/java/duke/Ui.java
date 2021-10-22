package duke;

import java.util.List;

import duke.task.Task;

/**
 * Handles all UI elements for duke.Duke.
 */
public class Ui {
    /**
     * Greets the user when Duke is started.
     */
    public static String greet() {
        return "Hello, I'm Duke! I'm here to help you with ANYTHING";
    }

    /**
     * Prints the list of tasks stored by Duke.
     * @param tasks list of Tasks
     */
    public static String printTasks(TaskList tasks) {
        if (tasks.size() == 0) {
            // Inform user if nothing has been stored.
            return "The list is empty!";
        }

        StringBuilder sb = new StringBuilder();
        String[] listItems = new String[tasks.size()];

        for (int i = 0; i < tasks.size(); ++i) {
            sb.append(String.format("%d. %s\n", i + 1, tasks.getTask(i)));
        }

        return sb.toString();
    }

    /**
     * Notifies the user when a task is added
     * @param task Task that was added
     * @param index Position of the task in the list
     */
    public static String notifyAdd(Task task, int index) {
        return String.format("I have added a new %s!\n", task.getClass().getSimpleName().toLowerCase())
                + String.format("%d. %s", index, task);
    }

    /**
     * Notifies the user when a task is deleted.
     * @param task Task that was deleted
     * @param taskCount Number of remaining tasks left
     */
    public static String notifyDelete(Task task, int taskCount) {
        return "I have removed this task!\n"
                + String.format("   %s\n", task)
                + String.format("You have %d task%s left.", taskCount, taskCount == 1 ? "" : "s");
    }

    /**
     * Notifies the user when a task is marked done.
     * @param task task marked as done
     * @param index position of the task in the list
     */
    public static String notifyMarkDone(Task task, int index) {
        return "I have marked the task as done!" + String.format("%d. %s", index + 1, task);
    }

    /**
     * Notify user on results of find command.
     * @param results tasks found through find command
     */
    public static String notifyFindResults(List<TaskList.FindResult> results) {
        StringBuilder sb = new StringBuilder("Here are some tasks matching your search:\n");
        for (TaskList.FindResult result : results) {
            sb.append(String.format("%d. %s\n", result.index + 1, result.task));
        }

        return sb.toString();
    }

    /**
     * Says goodbye to the user.
     *
     * Used after the bye command.
     */
    public static String goodbye() {
        return "Bye bye, see you next time.";
    }

    public static String notifySort(SortOrder order) {
        return "Sorted your tasks by " + order.label;
    }
}
