package duke;

import java.util.List;

import duke.task.Task;

/**
 * This class contains some common utility methods regrading string.
 */
public class StringUtils {
    /**
     * Returns a string contains adding task message.
     *
     * @param task The given task.
     * @param size The size of the task list.
     * @return A string contains adding task message.
     */
    public static String getAddTasKMessage(Task task, int size) {
        String result = "Got it. I've added this task:\n";
        result += task.toString() + "\n";
        if (size > 1) {
            result += ("Now you have " + size + " tasks in the list.\n");
        } else {
            result += ("Now you have " + size + " task in the list.\n");
        }
        return result;
    }

    /**
     * Returns a string contains removing task message.
     *
     * @param task The given task.
     * @param size The size of the task list.
     * @return A string contains removing task message.
     */
    public static String getRemoveTaskMessage(Task task, int size) {
        String result = "Got it. I've removed this task:\n";
        result += task.toString() + "\n";
        if (size > 1) {
            result += ("Now you have " + size + " tasks in the list.\n");
        } else {
            result += ("Now you have " + size + " task in the list.\n");
        }
        return result;
    }

    /**
     * Returns a string representation of all the tasks in the given task list.
     *
     * @param tasks The given task list.
     * @return A string representation of all the tasks in the given task list.
     */
    public static String getTaskList(List<Task> tasks) {
        String result = "Here are the tasks in your list:\n";
        if (tasks.isEmpty()) {
            result += "No tasks\n";
        } else {
            for (int i = 0; i < tasks.size(); ++i) {
                result += ((i + 1) + ". " + tasks.get(i).toString() + "\n");
            }
        }
        return result;
    }

    /**
     * Returns a string representation of all the matching tasks.
     *
     * @param tasks The given task list.
     * @return A string representation of all the tasks in the given task list.
     */
    public static String getSearchMessage(List<Task> tasks) {
        String result = "Here are the matching tasks in your list:\n";
        if (tasks.isEmpty()) {
            result += "No matching tasks\n";
        } else {
            for (int i = 0; i < tasks.size(); ++i) {
                result += ((i + 1) + ". " + tasks.get(i).toString() + "\n");
            }
        }
        return result;
    }

    /**
     * Returns a String representation of the updated task.
     *
     * @param task The updated task.
     * @return A String representation of the updated task.
     */
    public static String getEditMessage(Task task) {
        StringBuffer result = new StringBuffer("Got it. Here is the updated task:\n");
        result.append(task.toString());
        result.append("\n");
        return result.toString();
    }

    /**
     * Returns the welcome message.
     *
     * @return The welcome message.
     */
    public static String getWelcomeMessage() {
        return "Hello, I am Hagician\nWhat can I do for you?\n";
    }
}
