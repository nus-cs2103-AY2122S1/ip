package duke.util;

import duke.exception.DescriptionEmptyException;
import duke.exception.DukeException;
import duke.exception.IncorrectFormatException;
import duke.exception.InvalidTaskTypeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.TaskList;
import duke.task.Todo;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {

    private TaskList taskList;
    private Storage storage;
    private Ui ui;
    final static String UNKNOWN_COMMAND_MESSAGE = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";

    /**
     * Constructor for Parser
     *
     * @param taskList the input TaskList
     * @param storage the input storage to update changes
     */
    public Parser(TaskList taskList, Storage storage, Ui ui) {
        this.taskList = taskList;
        this.storage = storage;
        this.ui = ui;
    }

    /**
     * Interprets the command by users and updates the system accordingly
     */
    public String parse(String message) {
        try {

            if (message.equals("list")) {
                return taskList.listTasks();

            } else if (message.startsWith("delete")) {
                String deleted = taskList.deleteTask(taskList.taskToDelete(message));
                storage.updateFile(taskList.getTasks());
                return deleted;

            } else if (message.startsWith("done")) {
                String checkedTask = taskList.markAsCheckedTask(taskList.getTaskIndex(message));
                storage.updateFile(taskList.getTasks());
                return checkedTask;

            } else if (message.startsWith("find")) {
                String keyword = message.substring(5);
                return taskList.findTask(keyword);

            }else if (message.startsWith("update")) {
                return taskList.updatePrompt();

            } else if (message.startsWith("edit-N/")) {
                String temp = message.substring(message.indexOf(" "));
                String newData = temp.substring(temp.indexOf(" "));
                String updateTask = taskList.updateTaskName(taskList.getTaskIndex(message), newData);
                storage.updateFile(taskList.getTasks());
                return updateTask;

            } else if (message.startsWith("edit-D/")) {
                String temp = message.substring(message.indexOf(" "));
                String newData = temp.substring(temp.indexOf(" ") + 1);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                LocalDateTime parsedDate = LocalDateTime.parse(newData, formatter);
                String updateTask = taskList.updateTaskDuration(taskList.getTaskIndex(message), parsedDate);
                storage.updateFile(taskList.getTasks());
                return updateTask;

            } else if (message.startsWith("todo")) {
                isValidEntry(message, "todo");
                String newTodo = taskList.addTask(new Todo(getTaskName(message), false));
                storage.updateFile(taskList.getTasks());
                return newTodo;

            } else if (message.startsWith("deadline")) {
                isValidEntry(message, "deadline");
                isFormatCorrect(message, "deadline");
                String deadline = taskList
                        .addTask(new Deadline(getTaskName(message), getDuration(message), false));
                storage.updateFile(taskList.getTasks());
                return deadline;

            } else if (message.startsWith("event")) {
                isValidEntry(message, "event");
                isFormatCorrect(message, "event");
                String event = taskList
                        .addTask(new Event(getTaskName(message), getDuration(message), false));
                storage.updateFile(taskList.getTasks());
                return event;

            } else {
                notValid();
            }
        } catch (DukeException | IOException e) {
            return e.getMessage();

        } catch (DateTimeParseException e) {
            String errorMessage = "Format of date and time should be \n yyyy-MM-dd HH:mm";
            return errorMessage + "\n Please try again.";
        }
        return UNKNOWN_COMMAND_MESSAGE;
    }

    /**
     * Gets the name of the task
     *
     * @param message the input message by the user
     * @return a String that represents the name of the task
     */
    private String getTaskName(String message) {
        if (message.startsWith("todo")) {
            return message.substring(message.indexOf(" "));
        } else {
            return message.substring(message.indexOf(" "), message.indexOf("/"));
        }
    }

    /**
     * Gets the duration of the task
     *
     * @param message the input message by the user
     * @return a LocalDateTime that represents the Date and Time specified by user
     */
    private LocalDateTime getDuration(String message) {
        String temp = message.substring(message.indexOf("/") + 1);
        String due = temp.substring(temp.indexOf(" ") + 1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return LocalDateTime.parse(due, formatter);

    }

    /**
     * Checks if the input by user is valid
     *
     * @param message input message by the user
     * @param type the type of task
     * @throws DescriptionEmptyException if the task description is empty
     */
    public void isValidEntry(String message, String type) throws DescriptionEmptyException {
        if (message.length() <= type.length() || message.substring(message.indexOf(" ")).isBlank()) {
            throw new DescriptionEmptyException("☹ OOPS!!! The description of a " + type + " cannot be empty.");
        }
    }

    /**
     * Checks if the input has the correct format
     *
     * @param message input message by the user
     * @param type the type of task
     * @throws IncorrectFormatException if the format the user input is wrong
     */
    public static void isFormatCorrect(String message, String type) throws IncorrectFormatException {
        if (type.equals("deadline")) {
            if (!message.contains("/by")) {
                throw new IncorrectFormatException("Input format is incorrect. Please input again in this format : \n"
                        + " <task name> /by yyyy-MM-dd HH:mm");
            }
        }else {
            if (type.equals("event") && !message.contains("/at")) {
                throw new IncorrectFormatException("Input format is incorrect. Please input again in this format : \n"
                        + " <event name> /at yyyy-MM-dd HH:mm");
            }
        }
    }

    public static void notValid() throws InvalidTaskTypeException {
        throw new InvalidTaskTypeException(UNKNOWN_COMMAND_MESSAGE);
    }
}
