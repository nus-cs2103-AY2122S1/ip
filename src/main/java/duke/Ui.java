package duke;

import duke.task.Task;

/**
 * A class in charge of the interaction to {@code Duke} users.
 */
public class Ui {
    private static final String GREETING_TEXT = "Hello, I'm Duke!\n"
            + "How can I help you?";

    private static final String FAREWELL_TEXT = "Why do you choose to leave me!";

    /**
     * Greets the users by printing the greeting text.
     */
    public static String greet() {
        return GREETING_TEXT;
    }

    /**
     * Farewells the users by printing the farewell message.
     */
    public static String farewell() {
        return FAREWELL_TEXT;
    }

    /**
     * Returns a message when a task is added to a {@code TaskList}.
     *
     * @param tasks The list to which a new task is added.
     * @param task  The task added to the list.
     * @return The message indicating the addition of the task.
     */
    public static String addTaskMessage(TaskList tasks, Task task) {
        return "Got it. I've added this task:\n    "
                + task
                + "\nTask(s) remaining in the list: "
                + tasks.size();
    }

    /**
     * Returns a message when a task is updated.
     *
     * @param taskNum The item number of the task.
     * @param task  The task added to the list.
     * @return The message indicating the update of the task.
     */
    public static String updateTaskMessage(int taskNum, Task task) {
        return "Got it. I've update the task as:\n    "
                + taskNum + ". " + task;
    }

    /**
     * Returns a message <strong>after</strong> a task is removed from the list.
     *
     * @param tasks The list from which a task is removed.
     * @param task  The task that is removed.
     * @return The message indicating the deletion of the task.
     */
    public static String removeTaskMessage(TaskList tasks, Task task) {
        int tasksSize = tasks.size();
        return "Noted. I've removed this task:\n    "
                + task
                + "\nTask(s) remaining in the list: "
                + tasksSize;
    }

    /**
     * Returns a message when a task is done.
     *
     * @param task The task list to be marked done.
     * @return The message indicating the task is done.
     */
    public static String taskDoneMessage(Task task) {
        return "Nice! I've marked this task as done:\n    " + task;
    }

    /**
     * Returns a message when a task is undone.
     *
     * @param task The task list to be undone.
     * @return The message indicating the task in undone.
     */
    public static String taskUndoneMessage(Task task) {
        return "Alright. I've undone this task:\n    " + task;
    }
}
