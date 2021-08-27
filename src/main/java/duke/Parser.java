package duke;

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
import java.util.Scanner;

public class Parser {

    private TaskList taskList;
    private Storage storage;

    public Parser(TaskList taskList, Storage storage) {
        this.taskList = taskList;
        this.storage = storage;
    }

    public void parse() {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String message = sc.nextLine();
            try {

                if (message.equals("bye")) {
                    break;

                } else if (message.equals("list")) {
                    taskList.listTasks();

                } else if (message.startsWith("delete")) {
                    taskList.deleteTask(taskList.taskToDelete(message));
                    storage.updateFile(taskList.getTasks());

                } else if (message.startsWith("done")) {
                    taskList.markAsCheckedTask(taskList.taskToCheck(message));
                    storage.updateFile(taskList.getTasks());

                }else if (message.startsWith("todo")) {
                    isValidEntry(message, "todo");
                    String taskName = message.substring(message.indexOf(" "));
                    taskList.addTask(new Todo(taskName, false));
                    storage.updateFile(taskList.getTasks());

                }else if (message.startsWith("deadline")) {
                    isValidEntry(message, "deadline");
                    isFormatCorrect(message, "deadline");
                    String taskName = message.substring(message.indexOf(" "), message.indexOf("/"));
                    String temp = message.substring(message.indexOf("/") + 1);
                    String due = temp.substring(temp.indexOf(" ") + 1);
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                    LocalDateTime parsedDate = LocalDateTime.parse(due, formatter);
                    taskList.addTask(new Deadline(taskName, parsedDate, false));
                    storage.updateFile(taskList.getTasks());

                }else if (message.startsWith("event")) {
                    isValidEntry(message, "event");
                    isFormatCorrect(message, "event");
                    String taskName = message.substring(message.indexOf(" "), message.indexOf("/"));
                    String temp = message.substring(message.indexOf("/") + 1);
                    String due = temp.substring(temp.indexOf(" ") + 1);
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                    LocalDateTime parsedDate = LocalDateTime.parse(due, formatter);
                    taskList.addTask(new Event(taskName, parsedDate, false));
                    storage.updateFile(taskList.getTasks());

                }else {
                    notValid();
                }
            } catch (DukeException | IOException e) {
                System.out.println(e.getMessage());

            } catch (DateTimeParseException e) {
                String errorMessage = "Format of date and time should be \n yyyy-MM-dd HH:mm";
                System.out.println(errorMessage + "\n Please try again.");
            }
        }
        sc.close();
    }

    public void isValidEntry(String message, String type) throws DescriptionEmptyException {
        if (message.length() <= type.length() || message.substring(message.indexOf(" ")).isBlank()) {
            throw new DescriptionEmptyException("☹ OOPS!!! The description of a " + type + " cannot be empty.");
        }
    }

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
        throw new InvalidTaskTypeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}
