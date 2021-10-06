package duke;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import duke.task.Task;

/**
 * This class represents a {@code Ui} and handles all the interactions with the
 * user.
 *
 * @author Elizabeth Chow
 */
public class Ui {
    private static final String HORIZONTAL_LINE = "-------------------------------------------------------------";
    private static final String ADD_FORMAT = "Got it. I've  added this task:\n  %s";
    private static final String ERROR_FORMAT = "☹ OOPS!!! %s";
    private static final String TASK_COUNT_FORMAT = "Now you have %d %s in the list.";
    private static final String DONE_FORMAT = "Nice! I've marked this task as done:\n  %s";
    private static final String DELETE_FORMAT = "Noted. I've removed this task:\n  %s";
    private static final String UPDATE_FORMAT = "Nice! I've updated this task:\n  %s";

    /**
     * Displays the welcome message when the app starts running.
     *
     * @return String representation of the welcome message.
     */
    public String showWelcome() {
        return printMessage("Hello! I'm Duke\nWhat can I do for you?");
    }

    /**
     * Displays a nicely formatted error message with the given message.
     *
     * @param message Error message to be displayed.
     *
     * @return String representation of the error message.
     */
    public String showError(String message) {
        return printMessage(String.format(ERROR_FORMAT, message));
    }

    /**
     * Displays a nicely formatted message of the new task and the current task
     * count.
     *
     * @param task       {@code Task} to be displayed.
     * @param taskLength Number of tasks.
     *
     * @return String representation of the task and the task count.
     */
    public String showNewTask(Task task, int taskLength) {
        return printMessageWithTaskCount(ADD_FORMAT, task, taskLength);
    }

    /**
     * Displays a nicely formatted message of the deleted task and the task count.
     *
     * @param task       {@code Task} to be displayed.
     * @param taskLength Number of tasks.
     *
     * @return String representation of the task and the task count.
     */
    public String showDeletedTask(Task task, int taskLength) {
        return printMessageWithTaskCount(DELETE_FORMAT, task, taskLength);
    }

    /**
     * Displays a nicely formatted message of the done task.
     *
     * @param task {@code Task} to be displayed.
     *
     * @return String representation of the task.
     */
    public String showDoneTask(Task task) {
        return printMessage(String.format(DONE_FORMAT, task));
    }

    public String showUpdatedTask(Task task) {
        return printMessage(String.format(UPDATE_FORMAT, task));
    }

    /**
     * Shows the exit message.
     */
    public String showExit() {
        return printMessage("Bye. Hope to see you again soon!");
    }

    /**
     * Displays the given tasks in a nicely formatted manner.
     *
     * @param tasks An ArrayList of {@code Task} to be displayed.
     *
     * @return String representation of the tasks.
     */
    public String showTasks(ArrayList<Task> tasks) {
        ArrayList<String> message = new ArrayList<>();
        if (tasks.isEmpty()) {
            message.add("Nothing in the list!");
        } else {
            message = IntStream.range(0, tasks.size())
                    .mapToObj(i -> String.format("%d. %s", i + 1, tasks.get(i).toString()))
                    .collect(Collectors.toCollection(ArrayList::new));
        }
        return printMessage(message);
    }

    /**
     * Displays the filtered tasks.
     *
     * @param tasks Tasks to be displayed.
     *
     * @return String representation of the tasks.
     */
    public String showFilteredTasks(ArrayList<Task> tasks) {
        ArrayList<String> message = new ArrayList<>();
        if (tasks.isEmpty()) {
            message.add("No matching tasks found in the list.");
        } else {
            message.add("Here are the matching tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                message.add(String.format("%d. %s", i + 1, tasks.get(i).toString()));
            }
        }
        return printMessage(message);
    }

    private void printWithTabIndent(String str) {
        System.out.println("\t" + str);
    }

    private void printLine() {
        printWithTabIndent(HORIZONTAL_LINE);
    }

    private String printMessage(String message) {
        printLine();
        printWithTabIndent(message.replace("\n", "\n\t"));
        printLine();
        return message;
    }

    private String printMessage(ArrayList<String> message) {
        String joinedMessages = String.join("\n", message);
        printLine();
        printWithTabIndent(joinedMessages.replace("\n", "\n\t"));
        printLine();
        return joinedMessages;
    }

    private String printMessageWithTaskCount(String format, Task task, int taskLength) {
        return printMessage(
                String.format(format + "\n" + TASK_COUNT_FORMAT, task, taskLength, taskLength == 1 ? "task" : "tasks"));
    }
}
