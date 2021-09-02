package duke.storage;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.stream.Stream;

import duke.exception.DukeExtractCommandException;
import duke.exception.DukeIoException;
import duke.exception.DukeTaskNumberOutOfBoundsException;
import duke.exception.DukeUnknownException;
import duke.listener.Message;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.EventDateTime;
import duke.task.Operation;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;
import duke.util.Parser;

/**
 * The is the Storage class for task operations.
 */
public class Storage {
    private static final String TASK_ADDED_MESSAGE = "Got it. I've added this task:";
    private static final String TASK_MARKED_MESSAGE = "Nice! I've marked this task as done:";
    private static final String TASK_REMOVED_MESSAGE = "Noted. I've removed this task:";
    private static final String TASK_CLEARED_MESSAGE = "All tasks are cleared.";
    private static final String TASK_LISTED_MESSAGE = "Here are the tasks in your list:";
    private static final String MATCHING_TASK_LISTED_MESSAGE
            = "Here are the matching tasks in your list:";

    private static final String REGEX_AT = " /at ";
    private static final String REGEX_BY = " /by ";
    private static final String REGEX_SPACE = " ";

    private final TaskList taskList;
    private final Message onMessage;

    /**
     * Constructs a Storage object.
     *
     * @param onMessage Message interface.
     */
    public Storage(Message onMessage) {
        taskList = new TaskList();
        this.onMessage = onMessage;
    }

    /**
     * Loads tasks.
     */
    public void loadTasks() {
        try {
            taskList.loadTasksFromFile();
        } catch (DukeIoException e) {
            onMessage.show(e.getMessage());
        }
    }

    /**
     * Returns operation from command.
     *
     * @param command Command from user input.
     */
    public Operation getOperation(String command) {
        try {
            return Parser.extractOperation(command);
        } catch (DukeExtractCommandException | DukeUnknownException e) {
            onMessage.show(e.getMessage());
        }
        return null;
    }

    private int getTaskNumber(String command) {
        int number = 0;
        try {
            number = Parser.extractTaskNumber(command);
        } catch (DukeExtractCommandException | NumberFormatException
                | DukeTaskNumberOutOfBoundsException e) {
            onMessage.show(e.getMessage());
        }
        return number;
    }

    private String getTaskDescription(String command) {
        String description = "";
        try {
            description = Parser.extractTaskDescription(command);
        } catch (DukeExtractCommandException e) {
            onMessage.show(e.getMessage());
        }
        return description;
    }

    /**
     * Adds task.
     *
     * @param command Command from user input.
     */
    public void addTask(String command) {
        Operation operation = getOperation(command);
        if (operation == null) {
            return;
        }
        String description = getTaskDescription(command);
        if (description.equals("")) {
            return;
        }
        switch (operation) {
        case TODO:
            addTodoTask(description);
            break;
        case DEADLINE:
            addDeadlineTask(description);
            break;
        case EVENT:
            addEventTask(description);
            break;
        default:
            break;
        }
    }

    private void addTodoTask(String description) {
        Todo todo = new Todo(description);
        taskList.addTask(todo);
        onMessage.show(TASK_ADDED_MESSAGE,
                "  " + todo.toString(),
                "Now you have " + taskList.size()
                        + " " + (taskList.size() <= 1 ? "task" : "tasks")
                        + " in the list.");
    }

    private void addDeadlineTask(String description) {
        try {
            String[] taskDetails = Parser.extractTaskDetails(description, REGEX_BY);
            String taskName = taskDetails[0];
            LocalDateTime byDateTime = Parser.extractDeadlineDateTime(taskDetails[1]);
            Deadline deadline = new Deadline(taskName, byDateTime);
            taskList.addTask(deadline);
            onMessage.show(TASK_ADDED_MESSAGE,
                    "  " + deadline.toString(),
                    "Now you have " + taskList.size()
                            + " " + (taskList.size() <= 1 ? "task" : "tasks")
                            + " in the list.");
        } catch (DukeExtractCommandException e) {
            onMessage.show(e.getMessage());
        }
    }

    private void addEventTask(String description) {
        try {
            String[] taskDetails = Parser.extractTaskDetails(description, REGEX_AT);
            String taskName = taskDetails[0];
            EventDateTime eventDateTime = Parser
                .extractEventDatetime(taskDetails[1], REGEX_SPACE);
            Event event = new Event(taskName, eventDateTime);
            taskList.addTask(event);
            onMessage.show(TASK_ADDED_MESSAGE,
                    "  " + event.toString(),
                    "Now you have " + taskList.size() + " "
                            + (taskList.size() <= 1 ? "task" : "tasks")
                            + " in the list.");
        } catch (DukeExtractCommandException e) {
            onMessage.show(e.getMessage());
        }
    }

    /**
     * Completes task by number extracted from command.
     *
     * @param command Command from user input.
     */
    public void completeTask(String command) {
        Operation operation = getOperation(command);
        if (operation == null) {
            return;
        }
        int number = getTaskNumber(command);
        if (number <= 0) {
            return;
        }
        try {
            taskList.completeTask(number);
            onMessage.show(TASK_MARKED_MESSAGE,
                    "  " + taskList.findTaskByNumber(number).toString()
            );
        } catch (DukeTaskNumberOutOfBoundsException e) {
            onMessage.show(e.getMessage());
        }
    }

    /**
     * Deletes task by number extracted from command.
     *
     * @param command Command from user input.
     */
    public void deleteTask(String command) {
        Operation operation = getOperation(command);
        if (operation == null) {
            return;
        }
        int number = getTaskNumber(command);
        if (number <= 0) {
            return;
        }
        try {
            Task task = taskList.deleteTask(number);
            onMessage.show(TASK_REMOVED_MESSAGE,
                    "  " + task.toString(),
                    "Now you have " + taskList.size()
                            + " " + (taskList.size() <= 1 ? "task" : "tasks")
                            + " in the list."
            );
        } catch (DukeTaskNumberOutOfBoundsException e) {
            onMessage.show(e.getMessage());
        }
    }

    /**
     * Clears all tasks.
     */
    public void clearTasks() {
        taskList.clearTasks();
        onMessage.show(TASK_CLEARED_MESSAGE);
    }

    /**
     * Prints tasks from TaskList.
     */
    public void listTasks() {
        onMessage.show(Stream.concat(
            Arrays.stream(new String[] {TASK_LISTED_MESSAGE}),
            Arrays.stream(taskList.printTasks())
        ).toArray(String[]::new));
    }

    /**
     * Saves tasks to file.
     */
    public void saveTasksToFile() {
        try {
            taskList.saveTasksToFile();
        } catch (DukeIoException e) {
            onMessage.show(e.getMessage());
        }
    }

    /**
     * Finds tasks by keyword extracted from command.
     *
     * @param command Command from user input.
     */
    public void findTasks(String command) {
        Operation operation = getOperation(command);
        if (operation == null) {
            return;
        }
        try {
            String keyword = Parser.extractKeyword(command);
            onMessage.show(Stream.concat(
                    Arrays.stream(new String[]{MATCHING_TASK_LISTED_MESSAGE}),
                    Arrays.stream(taskList.findTasks(keyword))
            ).toArray(String[]::new));
        } catch (DukeExtractCommandException e) {
            onMessage.show(e.getMessage());
        }
    }
}
