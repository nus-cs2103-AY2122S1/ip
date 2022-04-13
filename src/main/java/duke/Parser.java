package duke;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

/**
 * Parser class containing static methods for the parsing of Duke commands and
 * inputs.
 */
public class Parser {
    private static void addTask(TaskList tasks, Storage storage, Task task) throws IOException {
        tasks.add(task);
        storage.writeTaskList(tasks);
    }

    private static Task popTask(TaskList tasks, Storage storage, int i) throws IOException {
        final Task task = tasks.get(i);
        tasks.remove(i);
        storage.writeTaskList(tasks);
        return task;
    }

    private static Task toggleTask(TaskList tasks, Storage storage, int i, boolean isDone) throws IOException {
        final Task task = tasks.get(i);
        task.toggle(isDone);
        storage.writeTaskList(tasks);
        return task;
    }

    /**
     * Executes a command on the input task list and storage objects, given
     * an input.
     * @param tasks List of tasks.
     * @param storage Storage object.
     * @param input Command input.
     * @return Boolean flag representing whether the program should terminate.
     * If the flag is true, the program should terminate; otherwise, the program
     * should continue running.
     * @throws Exception
     */
    public static String runCommand(TaskList tasks, Storage storage, String input) throws Exception {
        final var parameters = input.split(" ");
        assert parameters.length > 0;
        final var command = parameters[0];
        final var remaining = input.replace(command, "").strip();

        if (command.equals("list")) {
            if (!remaining.equals("")) {
                throw new Exception("The list command cannot take in any parameters.");
            }
            return Duke.renderTaskList(tasks);
        } else if (command.equals("done")) {
            final int i = Integer.parseInt(remaining) - 1;
            final Task task = toggleTask(tasks, storage, i, true);
            return String.join(
                    "\n",
                    "Nice! I've marked this task as done:",
                            "  " + Duke.renderTask(task)
            );
        } else if (command.equals("delete")) {
            final int i = Integer.parseInt(remaining) - 1;
            final Task task = popTask(tasks, storage, i);
            return String.join(
                    "\n",
                    "Noted. I've removed this task:",
                    "  " + Duke.renderTask(task),
                    String.format("Now you have %d tasks in the list.", tasks.size())
            );
        } else if (command.equals("todo")) {
            if (remaining.equals("")) {
                throw new Exception("The description of a todo cannot be empty.");
            }
            final Task task = Parser.parseTaskLine(tasks, remaining, TaskType.TODO);
            addTask(tasks, storage, task);
            return Duke.renderTaskAdded(tasks, task);
        } else if (command.equals("deadline") && remaining.contains("/by")) {
            final Task task = Parser.parseTaskLine(tasks, remaining, TaskType.DEADLINE);
            addTask(tasks, storage, task);
            return Duke.renderTaskAdded(tasks, task);
        } else if (command.equals("event") && remaining.contains("/at")) {
            final Task task = Parser.parseTaskLine(tasks, remaining, TaskType.EVENT);
            addTask(tasks, storage, task);
            return Duke.renderTaskAdded(tasks, task);
        } else if (command.equals("find")) {
            if (remaining.equals("")) {
                throw new Exception("The search query cannot be empty.");
            }
            return Duke.renderFoundTasks(tasks.find(remaining));
        } else {
            throw new Exception("I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * Parses a task line into a task.
     * @param taskLine Task line.
     * @param taskType Type of task.
     * @return Task object corresponding to the input parameters.
     */
    public static Task parseTaskLine(TaskList tasks, String taskLine, TaskType taskType) {
        switch (taskType) {
        case TODO:
            return new Todo(taskLine);
        case DEADLINE:
            final String[] deadlineParts = taskLine.split("\\s+/by\\s+", 2);
            assert deadlineParts.length == 2;
            final String deadlineDescription = deadlineParts[0];
            final LocalDate deadlineDate = LocalDate.parse(deadlineParts[1]);
            return new Deadline(deadlineDescription, deadlineDate);
        case EVENT:
            final String[] eventParts = taskLine.split("\\s+/at\\s+", 2);
            assert eventParts.length == 2;
            final String eventDescription = eventParts[0];
            final LocalDate eventDate = LocalDate.parse(eventParts[1]);

            final Optional<Event> clashingEvent = tasks.findEvent(eventDate);
            if (clashingEvent.isPresent()) {
                throw new IllegalArgumentException(String.join(
                        "\n",
                        "Event date clashes with another event:",
                        "  " + Duke.renderTask(clashingEvent.get())
                ));
            }

            return new Event(eventDescription, eventDate);
        default:
            throw new UnsupportedOperationException("task type is not a valid enum value");
        }
    }
}
