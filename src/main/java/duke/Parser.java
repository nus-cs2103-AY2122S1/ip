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
 * A class for making sense of the user string
 * Bridges communication between the user string and the tasks
 */
public class Parser {
    /** Whether Duke should continue running after handling **/
    public static boolean isRunning = true;

    /** TaskList object used by Duke **/
    private static TaskList tasklist;

    /** Current user string **/
    private static String command;

    /** Current user string split into words **/
    private static String[] commandArr;

    /**
     * Handles / parses user input, and allowing interactions with tasks
     * @param userString String that users enter
     * @param tasklist TaskList object used by Duke
     * @throws DukeException If Duke does not recognize the format provided
     */
    public static void handle(String userString, TaskList tasklist) throws DukeException {
        Parser.command = userString;
        Parser.tasklist = tasklist;
        String[] arr = userString.split(" ");
        String firstWord = arr[0];
        Parser.commandArr = arr;

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

    /**
     * Triggered when the done command is identified
     * Marks the given task as done
     * @throws DukeException If there is no task number or wrong task number
     */
    public static void handleDone() throws DukeException {
        if (commandArr.length < 2) {
            throw new MissingTaskNumberException("Missing task number");
        }
        int taskNumber = Integer.parseInt(commandArr[1]);
        try {
            tasklist.get(taskNumber).setDone(true);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTaskNumberException("Task does not exist");
        }
        UI.done(taskNumber);
        tasklist.list();
    }

    /**
     * Triggered when the todo command is identified
     * Adds a new Todo object to the TaskList
     * @throws DukeException If no name is provided
     */
    public static void handleTodo() throws DukeException {
        if (commandArr.length < 2) {
            throw new MissingTaskNameException("Missing task name");
        }
        String remaining = command.substring(5);
        tasklist.add(new ToDo(remaining));
        UI.added("todo");
        UI.numberOfTasks(tasklist.size());
    }


    /**
     * Triggered when the deadline command is identified
     * Adds a new Deadline object to the TaskList
     * @throws DukeException If no name or no deadline is provided
     */
    public static void handleDeadline() throws DukeException {
        if (commandArr.length < 2) {
            throw new MissingTaskNameException("Missing task name");
        }
        int byIndex = command.indexOf("/by");
        if (byIndex == -1) {
            throw new MissingDeadlineException("Missing deadline");
        }
        String deadlineName = command.substring(9, byIndex - 1);
        String deadlineByString = command.substring(byIndex + 4);
        LocalDate deadlineBy = LocalDate.parse(deadlineByString);
        tasklist.add(new Deadline(deadlineName, deadlineBy));
        UI.added("deadline");
        UI.numberOfTasks(tasklist.size());
    }

    /**
     * Triggered when event command is identified
     * Adds a new Event object to the TaskList
     * @throws DukeException If no task name or event date is provided
     */
    public static void handleEvent() throws DukeException {
        if (commandArr.length < 2) {
            throw new MissingTaskNameException("Missing task name");
        }
        int atIndex = command.indexOf("/at");
        if (atIndex == -1) {
            throw new MissingEventTimeException("Missing event time");
        }
        String eventName = command.substring(6, atIndex - 1);
        String eventAtString = command.substring(atIndex + 4);
        LocalDate eventAt = LocalDate.parse(eventAtString);
        tasklist.add(new Event(eventName, eventAt));
        UI.added("event");
        UI.numberOfTasks(tasklist.size());
    }

    /**
     * Triggered when delete command is identified
     * Deletes the task with the task number provided
     * @throws DukeException If there is none or invalid task number provided
     */
    public static void handleDelete() throws DukeException {
        if (commandArr.length < 2) {
            throw new MissingTaskNumberException("Missing task number");
        }
        int deleteTaskNumber = Integer.parseInt(commandArr[1]);
        try {
            tasklist.remove(deleteTaskNumber);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTaskNumberException("Task does not exist");
        }
        UI.delete(deleteTaskNumber);
        UI.numberOfTasks(tasklist.size());
        tasklist.list();
    }
}
