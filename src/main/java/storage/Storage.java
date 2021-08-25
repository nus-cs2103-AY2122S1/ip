package storage;

import exception.DukeExtractCommandException;
import exception.DukeIOException;
import exception.DukeTaskNumberOutOfBoundsException;
import exception.DukeUnknownException;
import listener.Message;
import task.Deadline;
import task.Event;
import task.EventDateTime;
import task.Operation;
import task.Task;
import task.TaskList;
import task.Todo;
import util.Parser;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * The is the Storage class for task operations.
 *
 * @author  HU JIAJUN
 * @version %I%, %G%
 * @since   1.0
 */

public class Storage {
    private final TaskList taskList;
    private final Message onMessage;

    /**
     * This is constructor method of Storage.
     */
    public Storage(Message onMessage) {
        taskList = new TaskList();
        this.onMessage = onMessage;
    }

    /**
     * Load tasks.
     */
    public void loadTasks() {
        try {
            taskList.loadTasksFromFile();
        } catch (DukeIOException e) {
            onMessage.show(e.getMessage());
        }
    }

    /**
     * Get operation from command.
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
        } catch (DukeExtractCommandException | NumberFormatException |
            DukeTaskNumberOutOfBoundsException e) {
            onMessage.show(e.getMessage());
        }
        return number;
    }

    private String getKeyword(String command) {
        String keyword = "";
        try {
            keyword = Parser.extractKeyword(command);
        } catch (DukeExtractCommandException e) {
            onMessage.show(e.getMessage());
        }
        return keyword;
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
     * Add task.
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
                Todo todo = new Todo(description);
                taskList.addTask(todo);
                onMessage.show(
                    "Got it. I've added this task:",
                    "  " + todo.toString(),
                    "Now you have " + taskList.size() + " " +
                        (taskList.size() <= 1 ? "task" : "tasks") + " in the list.");
                break;
            case DEADLINE:
                try {
                    String[] taskDetails = Parser.extractTaskDetails(description, " /by ");
                    String taskName = taskDetails[0];
                    LocalDateTime byDateTime = Parser.extractDeadlineDateTime(taskDetails[1]);
                    Deadline deadline = new Deadline(taskName, byDateTime);
                    taskList.addTask(deadline);
                    onMessage.show(
                        "Got it. I've added this task:",
                        "  " + deadline.toString(),
                        "Now you have " + taskList.size() + " " +
                            (taskList.size() <= 1 ? "task" : "tasks") + " in the list.");
                } catch (DukeExtractCommandException e) {
                    onMessage.show(e.getMessage());
                }
                break;
            case EVENT:
                try {
                    String[] taskDetails = Parser.extractTaskDetails(description, " /at ");
                    String taskName = taskDetails[0];
                    EventDateTime
                        eventDateTime = Parser.extractEventDatetime(taskDetails[1], " ");
                    Event event = new Event(taskName, eventDateTime);
                    taskList.addTask(event);
                    onMessage.show("Got it. I've added this task:",
                        "  " + event.toString(),
                        "Now you have " + taskList.size() + " " +
                            (taskList.size() <= 1 ? "task" : "tasks") + " in the list.");
                } catch (DukeExtractCommandException e) {
                    onMessage.show(e.getMessage());
                }
                break;
            default:
                break;
        }
    }

    /**
     * Complete task by number extracted from command.
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
            onMessage.show(
                "Nice! I've marked this task as done:",
                "  " + taskList.findTaskByNumber(number).toString()
            );
        } catch (DukeTaskNumberOutOfBoundsException e) {
            onMessage.show(e.getMessage());
        }
    }

    /**
     * Delete task by number extracted from command.
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
            onMessage.show(
                "Noted. I've removed this task:",
                "  " + task.toString(),
                "Now you have " + taskList.size() + " " +
                    (taskList.size() <= 1 ? "task" : "tasks") + " in the list.");
        } catch (DukeTaskNumberOutOfBoundsException e) {
            onMessage.show(e.getMessage());
        }
    }

    /**
     * Clear all tasks.
     */
    public void clearTasks() {
        taskList.clearTasks();
        onMessage.show("All tasks are cleared.");
    }

    /**
     * Print tasks from TaskList with format:
     *      1. Task1
     *      2. Task2
     *      ...
     */
    public void listTasks() {
        onMessage.show(Stream.concat(
            Arrays.stream(new String[] {"Here are the tasks in your list:"}),
            Arrays.stream(taskList.printTasks())
        ).toArray(String[]::new));
    }

    /**
     * Save tasks to file.
     */
    public void saveTasksToFile() {
        try {
            taskList.saveTasksToFile();
        } catch (DukeIOException e) {
            onMessage.show(e.getMessage());
        }
    }

    /**
     * Find tasks by keyword extracted from command.
     */
    public void findTasks(String command) {
        Operation operation = getOperation(command);
        if (operation == null) {
            return;
        }
        try {
            String keyword = Parser.extractKeyword(command);
            onMessage.show(Stream.concat(
                Arrays.stream(new String[]{"Here are the matching tasks in your list:"}),
                Arrays.stream(taskList.findTasks(keyword))
            ).toArray(String[]::new));
        } catch (DukeExtractCommandException e) {
            onMessage.show(e.getMessage());
        }
    }
}
