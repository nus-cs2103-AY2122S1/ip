package duke.util;

import duke.tasks.Task;
import duke.tasks.TaskList;

/**
 * The Ui class that deals with interactions with the user.
 */
public class Ui {
    private static final String INDENT = "   ";
    private final TaskList taskList;
    public Ui(TaskList tasklist) {
        this.taskList = tasklist;
    }

    /**
     * Format Duke output String consistently.
     *
     * @param str String representing a formatted Duke output.
     */
    public String formatOutput(String str) {
        StringBuilder sb = new StringBuilder();
        str.lines().forEach(line -> sb.append(INDENT).append(line).append('\n'));
        return sb.toString();
    }

    /**
     * Format Task to a desired add task output String.
     *
     * @param task String representing the Task just added.
     */
    public String addTaskOutput(Task task) {
        return "Got it. I've added this task:\n"
                + INDENT
                + task.toString()
                + "\nNow you have "
                + taskList.getNumOfTasks()
                + " tasks in your list.";
    }

    /**
     * Format Task to a desired delete task output String.
     *
     * @param task String representing the Task just deleted.
     */
    public String deleteTaskOutput(Task task) {
        return "Noted. I've removed this task:\n"
                + INDENT
                + task.toString()
                + "\nNow you have "
                + taskList.getNumOfTasks()
                + " tasks in your list.";
    }
}
