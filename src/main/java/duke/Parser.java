package duke;

import java.time.LocalDate;

import duke.exceptions.DukeException;
import duke.exceptions.InvalidCommandException;
import duke.exceptions.InvalidTaskNumberException;
import duke.exceptions.MissingDeadlineException;
import duke.exceptions.MissingEventTimeException;
import duke.exceptions.MissingTaskNameException;
import duke.exceptions.MissingTaskNumberException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;

/**
 * Makes sense of the user command
 * Bridges communication between the user string and the tasks
 */
public class Parser {
    /** Whether Duke should continue running after handling **/
    private static boolean isRunning = true;

    /** TaskList object used by Duke **/
    private static TaskList taskList;

    /** Current user string **/
    private static String userString;

    /** Current user string split into words **/
    private static String[] words;

    /**
     * Handles / parses user input, and allowing interactions with tasks
     *
     * @param userString String that users enter
     * @param taskList TaskList object used by Duke
     * @throws DukeException If Duke does not recognize the format provided
     */
    public static void handle(String userString, TaskList taskList) throws DukeException {
        Parser.userString = userString;
        Parser.taskList = taskList;
        String[] arr = userString.split(" ");
        String firstWord = arr[0];
        Parser.words = arr;

        switch (firstWord) {
        case "bye":
            taskList.saveTasksToStorage();
            UI.bye();
            isRunning = false;
            break;
        case "list":
            taskList.list();
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
        case "find":
            handleFind();
            break;
        default:
            throw new InvalidCommandException("Invalid command");
        }
    }

    /**
     * Triggered when the done command is identified
     * Marks the given task as done
     *
     * @throws DukeException If there is no task number or wrong task number
     */
    public static void handleDone() throws DukeException {
        if (words.length < 2) {
            throw new MissingTaskNumberException("Missing task number");
        }
        int taskNumber = Integer.parseInt(words[1]);
        try {
            taskList.get(taskNumber).setDone(true);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTaskNumberException("Task does not exist");
        }
        UI.done(taskNumber);
        taskList.list();
    }

    /**
     * Triggered when the todo command is identified
     * Adds a new Todo object to the TaskList
     *
     * @throws DukeException If no name is provided
     */
    public static void handleTodo() throws DukeException {
        if (words.length < 2) {
            throw new MissingTaskNameException("Missing task name");
        }
        String remaining = userString.substring(5);
        taskList.add(new ToDo(remaining));
        UI.added("todo");
        UI.numberOfTasks(taskList.size());
    }


    /**
     * Triggered when the deadline command is identified
     * Adds a new Deadline object to the TaskList
     *
     * @throws DukeException If no name or no deadline is provided
     */
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

    /**
     * Triggered when event command is identified
     * Adds a new Event object to the TaskList
     *
     * @throws DukeException If no task name or event date is provided
     */
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

    /**
     * Deletes the task with the task number provided
     * Triggered when delete command is identified
     *
     * @throws DukeException If there is none or invalid task number provided
     */
    public static void handleDelete() throws DukeException {
        if (words.length < 2) {
            throw new MissingTaskNumberException("Missing task number");
        }
        int deleteTaskNumber = Integer.parseInt(words[1]);
        try {
            taskList.remove(deleteTaskNumber);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTaskNumberException("Task does not exist");
        }
        UI.delete(deleteTaskNumber);
        UI.numberOfTasks(taskList.size());
        taskList.list();
    }

    /**
     * Prints tasks that match the user string
     * Triggered when find command is identified
     * @throws MissingTaskNameException If no task name provided
     */
    public static void handleFind() throws MissingTaskNameException {
        if (words.length < 2) {
            throw new MissingTaskNameException("Missing task name");
        }
        taskList.find(words[1]);
    }

    public static boolean isRunning() {
        return Parser.isRunning();
    }
}
