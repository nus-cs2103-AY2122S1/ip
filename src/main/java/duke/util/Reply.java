package duke.util;

import duke.task.Task;

/**
 * A static class that help generate the text replies from Duke
 */
public class Reply {
    /**
     * Constructor of Reply class.
     */
    private Reply() { }
    /**
     * Shows the welcome text.
     */
    public static String showWelcome() {
        return "Hello, I am Duke. What can I do for you?";
    }

    /**
     * Shows the error given an error message.
     *
     * @param errMessage Error message.
     */
    public static String showError(String errMessage) {
        return String.format("Ooop! %s\nPlease try again.", errMessage);
    }

    /**
     * Shows that the user has successfully added a new task.
     *
     * @param task Task that has been added.
     * @param tasks Tasklist which the task has been added to.
     */
    public static String showTaskAdded(Task task, TaskList tasks) {
        assert task != null;
        assert tasks != null;

        return String.format("Got it. I've added this task: \n   %s \nNow you have %d task in the list.\n",
                task, tasks.size());
    }

    /**
     * Shows that the user has successfully removed a task.
     *
     * @param task Task that has been removed
     * @param tasks Tasklist which the task has been added to.
     */
    public static String showTaskRemoved(Task task, TaskList tasks) {
        assert task != null;
        assert tasks != null;
        return String.format("Noted. I've removed this task:\n  %s\nNow you have %d task in the list.\n",
                task, tasks.size());
    }

    /**
     * Shows that the user has successfully marked a task as completed.
     *
     * @param task Task that has been completed.
     */
    public static String showTaskDone(Task task) {
        assert task != null;
        return String.format("Nice! I've marked this task as done:\n%s", task);
    }

    /**
     * Shows a user friendly text version of the tasks in the tasklist.
     */
    public static String showList(TaskList tasks) {
        assert tasks != null;
        StringBuilder out = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            out.append(String.format("%d. %s\n", i + 1, tasks.get(i)));
        }
        return out.toString();
    }

    /**
     * Shows the tasks which matches the search terms.
     *
     * @param tasks Tasklist of tasks that matches the search terms.
     */
    public static String showMatchingTasks(TaskList tasks) {
        assert tasks != null;
        if (tasks.size() == 0) {
            return "There are no matches in your list.\nUse command 'list' to see your whole list";
        } else {
            StringBuilder out = new StringBuilder("Here are the matching tasks in your list: \n");
            for (int i = 0; i < tasks.size(); i++) {
                out.append(String.format("%d. %s\n", i + 1, tasks.get(i)));
            }
            return out.toString();
        }
    }

    /**
     * Shows a goodbye message.
     */
    public static String showBye() {
        return "Bye. Hope to see you again!";
    }

    public static String showTag(Task task) {
        return String.format("You added a tag:\n %s", task);
    }
}
