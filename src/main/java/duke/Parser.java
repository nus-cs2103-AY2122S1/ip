package duke;

import java.time.LocalDate;

import duke.exceptions.*;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;

/**
 * Makes sense of the user command
 * Bridges communication between the user and the tasks
 */
public class Parser {
    public static boolean isRunning = true;
    private static TaskList taskList;
    private static String userString;
    private static String[] words;
    /**
     * Handles user input regarding tasks
     *
     * @param userString String that users enter
     */
    public static void handle(String userString, TaskList tasklist) throws DukeException {
        Parser.userString = userString;
        Parser.taskList = tasklist;
        String[] arr = userString.split(" ");
        String firstWord = arr[0];
        Parser.words = arr;

        switch (firstWord) {
        case "bye":
            tasklist.saveTasksToStorage();
            UI.bye();
            isRunning = false;
            break;
        case "list":
            tasklist.list();
            break;
        case "done":
            handleDone();
            break;
        case "todo":
            handleTodo();
            break;
        case "deadline":
            handleDeadline();
            break;
        case "event":
            handleEvent();
            break;
        case "delete":
            handleDelete();
            break;
        default:
            throw new InvalidCommandException("Invalid command");
        }
    }

    public static void handleDone() throws DukeException {
        if (words.length < 2) {
            throw new MissingTaskNumberException("Missing task number");
        }
        int taskNumber = Integer.parseInt(words[1]);
        try {
            taskList.get(taskNumber).setDone(true);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTaskNumberException("Duke.Task does not exist");
        }
        UI.done(taskNumber);
        taskList.list();
    }

    public static void handleTodo() throws DukeException {
        if (words.length < 2) {
            throw new MissingTaskNameException("Missing task name");
        }
        String remaining = userString.substring(5);
        taskList.add(new ToDo(remaining));
        UI.added("todo");
        UI.numberOfTasks(taskList.size());
    }

    public static void handleDeadline() throws DukeException {
        if (words.length < 2) {
            throw new MissingTaskNameException("Missing task name");
        }
        int byIndex = userString.indexOf("/by");
        if (byIndex == -1) {
            throw new MissingDeadlineException("Missing deadline");
        }
        String deadlineName = userString.substring(9, byIndex - 1);
        String deadlineByString = userString.substring(byIndex + 4);
        LocalDate deadlineBy = LocalDate.parse(deadlineByString);
        taskList.add(new Deadline(deadlineName, deadlineBy));
        UI.added("deadline");
        UI.numberOfTasks(taskList.size());
    }

    public static void handleEvent() throws DukeException {
        if (words.length < 2) {
            throw new MissingTaskNameException("Missing task name");
        }
        int atIndex = userString.indexOf("/at");
        if (atIndex == -1) {
            throw new MissingEventTimeException("Missing event time");
        }
        String eventName = userString.substring(6, atIndex - 1);
        String eventAtString = userString.substring(atIndex + 4);
        LocalDate eventAt = LocalDate.parse(eventAtString);
        taskList.add(new Event(eventName, eventAt));
        UI.added("event");
        UI.numberOfTasks(taskList.size());
    }

    public static void handleDelete() throws DukeException {
        if (words.length < 2) {
            throw new MissingTaskNumberException("Missing task number");
        }
        int deleteTaskNumber = Integer.parseInt(words[1]);
        try {
            taskList.remove(deleteTaskNumber);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTaskNumberException("Duke.Task does not exist");
        }
        UI.delete(deleteTaskNumber);
        UI.numberOfTasks(taskList.size());
        taskList.list();
    }
}
