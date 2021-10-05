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
    private final TaskList taskList;

    /** Current user string **/
    private String userString = "";

    /** Current user string split into words **/
    private String[] words;

    /** Whether the current command is bye **/
    private boolean isBye = false;

    public Parser(TaskList taskList) {
        this.taskList = taskList;
    }
    /**
     * Handles user input, and allowing interactions with tasks
     *
     * @param userString String that users enter
     * @throws DukeException If Duke does not recognize the format provided
     */
    public String handle(String userString) throws DukeException {
        this.userString = userString;
        String[] arr = userString.split(" ");
        String firstWord = arr[0].toLowerCase();
        words = arr;

        switch (firstWord) {
        case "hi": // fallthrough
        case "hello":
            return Ui.hi();
        case "bye":
            isBye = true;
            taskList.saveTasksToStorage();
            return Ui.bye();
        case "l": // fallthrough
        case "list":
            return Ui.list(taskList);
        case "done":
            return handleDone();
        case "t": // fallthrough
        case "todo":
            return handleTodo();
        case "d": // fallthrough
        case "deadline":
            return handleDeadline();
        case "e": // fallthrough
        case "event":
            return handleEvent();
        case "delete":
            return handleDelete();
        case "f": // fallthrough
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
    public String handleDone() throws DukeException {
        assert words[0].toLowerCase().equals("done") : "wrong method";
        if (words.length == 1) {
            throw new MissingTaskNumberException("Missing task number");
        }
        int taskNumber;
        try {
            taskNumber = Integer.parseInt(words[1]);
            taskList.get(taskNumber).setDone(true);
        } catch (NumberFormatException e) {
            throw new InvalidCommandException("Wrong format");
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
     * @return Duke response for handling ToDo
     * @throws DukeException If no name is provided
     */
    public String handleTodo() throws DukeException {
        assert words[0].toLowerCase().equals("todo") : "wrong method";
        if (words.length == 1) {
            throw new MissingTaskNameException("Missing task name");
        }
        int indexWhereTaskNameStarts = words[0].length() + 1;
        String remaining = userString.substring(indexWhereTaskNameStarts);
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
    public String handleDeadline() throws DukeException {
        assert words[0].toLowerCase().equals("deadline") : "wrong method";
        if (words.length == 1) {
            throw new MissingTaskNameException("Missing task name");
        }
        String deadlineName = getDeadlineNameFromString(userString);
        LocalDate dueDate = getDueDateFromString(userString);
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
    public String getDeadlineNameFromString(String userString) throws MissingDeadlineException {
        int indexOfBy = userString.indexOf("/by");
        if (indexOfBy == -1) {
            throw new MissingDeadlineException("Missing deadline");
        }
        int indexOfTaskName = words[0].length() + 1;
        return userString.substring(indexOfTaskName, indexOfBy - 1);
    }

    /**
     * Gets the due date from the user string
     *
     * @param userString String that user enters
     * @return LocalDate object of the due date
     * @throws MissingDeadlineException If no due date is provided
     */
    public LocalDate getDueDateFromString(String userString) throws MissingDeadlineException {
        int indexOfBy = userString.indexOf("/by");
        if (indexOfBy == -1) {
            throw new MissingDeadlineException("Missing deadline");
        }
        int indexOfDueDate = indexOfBy + 4;
        String dueDateString = userString.substring(indexOfDueDate);
        return LocalDate.parse(dueDateString);
    }

    /**
     * Adds a new Event object to the TaskList
     * Triggered when event command is identified
     *
     * @return Duke response for handling Event
     * @throws DukeException If no task name or event date is provided
     */
    public String handleEvent() throws DukeException {
        assert words[0].toLowerCase().equals("event") : "wrong method";
        if (words.length == 1) {
            throw new MissingTaskNameException("Missing task name");
        }
        String eventName = getEventNameFromString(userString);
        LocalDate eventDate = getEventDateFromString(userString);
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
    public String getEventNameFromString(String userString) throws MissingEventTimeException {
        int indexOfAt = userString.indexOf("/at");
        if (indexOfAt == -1) {
            throw new MissingEventTimeException("Missing event time");
        }
        int indexOfTaskName = words[0].length() + 1;
        return userString.substring(indexOfTaskName, indexOfAt - 1);
    }

    /**
     * Gets the date of the event task
     *
     * @param userString String that user enters
     * @return Date of the event task
     * @throws MissingEventTimeException If the event date is not provided
     */
    public LocalDate getEventDateFromString(String userString) throws MissingEventTimeException {
        int indexOfAt = userString.indexOf("/at");
        if (indexOfAt == -1) {
            throw new MissingEventTimeException("Missing event time");
        }
        String eventDateString = userString.substring(indexOfAt + 4);
        return LocalDate.parse(eventDateString);
    }

    /**
     * Deletes the task with the task number provided
     * Triggered when delete command is identified
     *
     * @return Duke response for handling delete
     * @throws DukeException If there is none or invalid task number provided
     */
    public String handleDelete() throws DukeException {
        assert words[0].toLowerCase().equals("delete") : "wrong method";
        if (words.length == 1) {
            throw new MissingTaskNumberException("Missing task number");
        }
        int deleteTaskNumber;
        try {
            deleteTaskNumber = Integer.parseInt(words[1]);
            taskList.remove(deleteTaskNumber);
        } catch (NumberFormatException e) {
            throw new InvalidCommandException("Wrong format");
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
    public String handleFind() throws MissingTaskNameException {
        assert words[0].toLowerCase().equals("find") : "wrong method";
        if (words.length == 1) {
            throw new MissingTaskNameException("Missing task name");
        }
        return taskList.find(words[1]);
    }

    public boolean isBye() {
        return isBye;
    }
}
