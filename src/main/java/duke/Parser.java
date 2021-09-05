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
 * Makes sense of the user command.
 * Bridges communication between the user string and the tasks.
 */
public class Parser {
    /** TaskList object used by Duke **/
    private static TaskList taskList;

    /** Current user string **/
    private static String userString;

    /** Current user string split into words **/
    private static String[] words;

    /**
     * Handles user input, and allowing interactions with tasks
     *
     * @param userString String that users enter
     * @param taskList TaskList object used by Duke
     * @throws DukeException If Duke does not recognize the format provided
     */
    public static String handle(String userString, TaskList taskList) throws DukeException {
        Parser.userString = userString;
        Parser.taskList = taskList;
        String[] arr = userString.split(" ");
        String firstWord = arr[0].toLowerCase();
        Parser.words = arr;

        switch (firstWord) {
        case "hi": // fallthrough
        case "hello":
            return Ui.hi();
        case "bye":
            taskList.saveTasksToStorage();
            return Ui.bye();
        case "list":
            return Ui.list(taskList);
        case "done":
            return handleDone();
        case "todo":
            return handleTodo();
        case "deadline":
            return handleDeadline();
        case "event":
            return handleEvent();
        case "delete":
            return handleDelete();
        case "find":
            return handleFind();
        default:
            throw new InvalidCommandException("Invalid command");
        }
    }

    /**
     * Marks the given task as done
     * Triggered when the done command is identified
     *
     * @return Duke response for handling done
     * @throws DukeException If there is no task number or wrong task number
     */
    public static String handleDone() throws DukeException {
        if (words.length == 1) {
            throw new MissingTaskNumberException("Missing task number");
        }
        int taskNumber = Integer.parseInt(words[1]);
        try {
            taskList.get(taskNumber).setDone(true);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTaskNumberException("Task does not exist");
        }
        taskList.saveTasksToStorage();
        return Ui.done(taskNumber)
                + "\n"
                + Ui.list(taskList);
    }

    /**
     * Adds a new ToDo object to the TaskList
     * Triggered when the todo command is identified
     *
     * @retyrn Duke response for handling ToDo
     * @throws DukeException If no name is provided
     */
    public static String handleTodo() throws DukeException {
        if (words.length == 1) {
            throw new MissingTaskNameException("Missing task name");
        }
        String remaining = userString.substring(5);
        taskList.add(new ToDo(remaining));
        taskList.saveTasksToStorage();
        return Ui.added("todo")
                + "\n" + Ui.numberOfTasks(taskList.size());
    }


    /**
     * Adds a new Deadline object to the TaskList
     * Triggered when the deadline command is identified
     *
     * @return Duke response for handling Deadline
     * @throws DukeException If no name or no deadline is provided
     */
    public static String handleDeadline() throws DukeException {
        if (words.length == 1) {
            throw new MissingTaskNameException("Missing task name");
        }
        String deadlineName = getDeadlineName(userString);
        LocalDate dueDate = getDueDate(userString);
        taskList.add(new Deadline(deadlineName, dueDate));
        taskList.saveTasksToStorage();
        return Ui.added("deadline")
                + "\n" + Ui.numberOfTasks(taskList.size());
    }

    /**
     * Gets the name of the deadline task
     *
     * @param userString String that user enters
     * @return Name of deadline task
     * @throws MissingDeadlineException If no due date is provided
     */
    public static String getDeadlineName(String userString) throws MissingDeadlineException {
        int byIndex = userString.indexOf("/by");
        if (byIndex == -1) {
            throw new MissingDeadlineException("Missing deadline");
        }
        return userString.substring(9, byIndex - 1);
    }

    /**
     * Gets the due date from the user string
     *
     * @param userString String that user enters
     * @return LocalDate object of the due date
     * @throws MissingDeadlineException If no due date is provided
     */
    public static LocalDate getDueDate(String userString) throws MissingDeadlineException {
        int byIndex = userString.indexOf("/by");
        if (byIndex == -1) {
            throw new MissingDeadlineException("Missing deadline");
        }
        String dueDateString = userString.substring(byIndex + 4);
        return LocalDate.parse(dueDateString);
    }

    /**
     * Adds a new Event object to the TaskList
     * Triggered when event command is identified
     *
     * @return Duke response for handling Event
     * @throws DukeException If no task name or event date is provided
     */
    public static String handleEvent() throws DukeException {
        if (words.length == 1) {
            throw new MissingTaskNameException("Missing task name");
        }
        String eventName = getEventName(userString);
        LocalDate eventDate = getEventDate(userString);
        taskList.add(new Event(eventName, eventDate));
        taskList.saveTasksToStorage();
        return Ui.added("event")
                + "\n" + Ui.numberOfTasks(taskList.size());
    }

    /**
     * Gets the name of the event task
     *
     * @param userString String that user enters
     * @return Name of event task
     * @throws MissingEventTimeException If the event date is not provided
     */
    public static String getEventName(String userString) throws MissingEventTimeException {
        int atIndex = userString.indexOf("/at");
        if (atIndex == -1) {
            throw new MissingEventTimeException("Missing event time");
        }
        return userString.substring(6, atIndex - 1);
    }

    /**
     * Gets the date of the event task
     *
     * @param userString String that user enters
     * @return Date of the event task
     * @throws MissingEventTimeException If the event date is not provided
     */
    public static LocalDate getEventDate(String userString) throws MissingEventTimeException {
        int atIndex = userString.indexOf("/at");
        if (atIndex == -1) {
            throw new MissingEventTimeException("Missing event time");
        }
        String eventDateString = userString.substring(atIndex + 4);
        return LocalDate.parse(eventDateString);
    }

    /**
     * Deletes the task with the task number provided
     * Triggered when delete command is identified
     *
     * @return Duke response for handling delete
     * @throws DukeException If there is none or invalid task number provided
     */
    public static String handleDelete() throws DukeException {
        if (words.length == 1) {
            throw new MissingTaskNumberException("Missing task number");
        }
        int deleteTaskNumber = Integer.parseInt(words[1]);
        try {
            taskList.remove(deleteTaskNumber);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTaskNumberException("Task does not exist");
        }
        taskList.saveTasksToStorage();
        return Ui.delete(deleteTaskNumber)
                + "\n" + Ui.numberOfTasks(taskList.size())
                + "\n" + Ui.list(taskList);
    }

    /**
     * Prints tasks that match the user string
     * Triggered when find command is identified
     *
     * @return Duke response for handling find
     * @throws MissingTaskNameException If no task name provided
     */
    public static String handleFind() throws MissingTaskNameException {
        if (words.length == 1) {
            throw new MissingTaskNameException("Missing task name");
        }
        return taskList.find(words[1]);
    }
}
