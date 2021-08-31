package duke;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Class containing methods for Duke functionality.
 */
public class Duke {
    private static final String WELCOME_MSG = String.join(
            "\n",
            "Hello from Duke!",
            "What can I do for you?"
    );
    private Storage storage;
    private TaskList tasks;

    public Duke(Storage storage) throws IOException {
        this.storage = storage;
        this.tasks = storage.readTaskList();
    }

    public String getResponse(String input) {
        try {
            return Parser.runCommand(this.tasks, this.storage, input);
        } catch (Exception e) {
            return renderException(e);
        }
    }

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
            throw new UnsupportedOperationException("task type is not a valid enum value");
        }
    }

    /**
     * Render welcome message.
     * @return Welcome message.
     */
    public static String renderWelcome() {
        return WELCOME_MSG;
    }

    /**
     * Render an exception message.
     * @param e Exception.
     * @return Exception message.
     */
    public static String renderException(Exception e) {
        return "☹ OOPS!!! " + e.getMessage();
    }

    /**
     * Renders a list of tasks.
     * @param tasks List of tasks.
     * @return String representing list of tasks.
     */
    public static String renderTaskList(TaskList tasks) {
        return String.join(
                "\n",
                Stream.concat(
                        Stream.of("Here are the tasks in your list:"),
                        IntStream.range(0, tasks.size())
                                .mapToObj(i -> String.format("%d. %s", i + 1, renderTask(tasks.get(i)))
                )
        ).toArray(String[]::new));
    }

    /**
     * Print banner for a newly added task.
     * @param tasks List of tasks.
     * @param task Newly added task.
     * @return String representing newly added task with list of tasks.
     */
    public static String renderTaskAdded(TaskList tasks, Task task) {
        return String.join(
                "\n",
                "Got it. I've added this task:",
                "  " + renderTask(task),
                String.format("Now you have %d tasks in the list.", tasks.size())
        );
    }

    /**
     * Print banner for found tasks.
     * @param foundTasks List of found tasks.
     * @return String representing list of found tasks.
     */
    public static String renderFoundTasks(TaskList foundTasks) {
        return String.join(
                "\n",
                Stream.concat(
                        Stream.of("Here are the matching tasks in your list:"),
                        IntStream.range(0, foundTasks.size())
                                .mapToObj(i -> String.format("%d. %s", i + 1, renderTask(foundTasks.get(i)))
                )
                ).toArray(String[]::new)
        );
    }
}
