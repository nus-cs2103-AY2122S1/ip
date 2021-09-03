package duke.util;

import duke.tasks.Task;
import duke.tasks.TaskList;

/**
 * The Ui class that deals with interactions with the user.
 */
public class Ui {
    private static final String INDENT = "      ";
    private static final String LINE =
            "     _______________________________________\n";
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private final TaskList taskList;

    public Ui(TaskList tasklist) {
        this.taskList = tasklist;
    }

    /**
     * Wrapper to render Duke output in a consistent format.
     *
     * @param str String representing Duke output.
     */
    public static String formatOutput(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append(LINE);
        str.lines().forEach(line -> sb.append("         ").append(line).append('\n'));
        sb.append(LINE);
        return sb.toString();
    }

    /**
     * Format Task to render desired add task output.
     *
     * @param task Task just added.
     */
    public String addTaskOutput(Task task) {
        String output =
                "Got it. I've added this task:\n"
                        + INDENT
                        + task.toString()
                        + "\nNow you have "
                        + taskList.length()
                        + " tasks in the list.";
        return formatOutput(output);
    }

    /**
     * Format Task to render desired delete task output.
     *
     * @param task Task just deleted.
     */
    public String deleteTaskOutput(Task task) {
        String output =
                "Noted. I've removed this task:\n"
                        + INDENT
                        + task.toString()
                        + "\nNow you have "
                        + taskList.length()
                        + " tasks in the list.";
        return formatOutput(output);
    }
}
