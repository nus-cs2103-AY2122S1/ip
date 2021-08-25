package duke;

import java.time.format.DateTimeFormatter;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Class containing methods for UI-related functionality.
 */
public class Ui {
    private static final String SEPARATOR =
            "____________________________________________________________";
    private static final String PREFIX = "\t";
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String WELCOME_MSG =
            "Hello from\n" + LOGO + "What can I do for you?";
    private static final String BYE_MSG = "Bye. Hope to see you again soon!";

    /**
     * Renders a task into a string.
     * @param task Task.
     * @return Input task rendered as a string.
     */
    public static String renderTask(Task task) {
        var taskType = TaskType.identifyTask(task);
        String taskIcon = taskType.getTaskIcon();
        String statusIcon = task.getStatusIcon();
        switch (taskType) {
            case TODO:
                return String.format(
                    "[%s][%s] %s",
                    taskIcon,
                    statusIcon,
                    task.getDescription()
                );
            case DEADLINE:
                var deadline = (Deadline) task;
                return String.format(
                    "[%s][%s] %s (by: %s)",
                    taskIcon,
                    statusIcon,
                    deadline.getDescription(),
                    deadline.getDeadline()
                        .format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                );
            case EVENT:
                var event = (Event) task;
                return String.format(
                    "[%s][%s] %s (at: %s)",
                    taskIcon,
                    statusIcon,
                    event.getDescription(),
                    event.getTime()
                        .format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                );
            default:
                throw new UnsupportedOperationException(
                    "task type is not a valid enum value"
                );
        }
    }

    /**
     * Print a banner.
     * 
     * Each string in the <code>lines</code> array is treated as a separate
     * line, and it is assumed that the string contains no newline characters.
     * 
     * @param lines Array of strings corresponding to lines of a message.
     */
    public static void printBanner(String[] lines) {
        System.out.println(PREFIX + SEPARATOR);
        for (String line : lines) {
            System.out.println(PREFIX + line);
        }
        System.out.println(PREFIX + SEPARATOR);
    }

    /**
     * Print welcome banner.
     */
    public static void printWelcome() {
        printBanner(WELCOME_MSG.split("\n"));
    }

    /**
     * Print bye banner.
     */
    public static void printBye() {
        printBanner(BYE_MSG.split("\n"));
    }

    /**
     * Print an exception banner.
     * @param e Exception.
     */
    public static void printException(Exception e) {
        printBanner(new String[]{ "☹ OOPS!!! " + e.getMessage() });
    }

    /**
     * Print a list of tasks.
     * @param tasks List of tasks.
     */
    public static void printTaskList(TaskList tasks) {
        printBanner(
            Stream.concat(
                Stream.of("Here are the tasks in your list:"),
                IntStream.range(0, tasks.size())
                    .mapToObj(i -> String.format(
                        "%d. %s",
                        i + 1,
                        renderTask(tasks.get(i)))
                    )
            ).toArray(String[]::new)
        );
    }

    /**
     * Print banner for a newly added task.
     * @param tasks List of tasks.
     * @param task Newly added task.
     */
    public static void printTaskAdded(TaskList tasks, Task task) {
        printBanner(new String[] {
            "Got it. I've added this task:",
            "  " + renderTask(task),
            String.format("Now you have %d tasks in the list.", tasks.size())
        });
    }
}
