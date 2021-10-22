package duke;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

/**
 * Parser class makes sense of user input.
 */
public class Parser {
    private final TaskList taskList;
    private final Storage storage;

    /**
     * Initialises Parser with taskList and storage.
     *
     * @param taskList the taskList from Duke.
     * @param storage the storage from Duke.
     */
    public Parser(TaskList taskList, Storage storage) {
        this.taskList = taskList;
        this.storage = storage;
    }

    /**
     * Makes sense of the user input.
     * Handles: bye, list, done, delete, todo, deadline, event, find, remind commands.
     *
     * @param input the user input from the Ui.
     * @return a boolean that determines if Duke is still inSession.
     * @throws DukeException is thrown when the input is invalid or unknown.
     */
    public String parseCommand(String input) throws DukeException {
        String action = input.split(" ", 2)[0].toLowerCase();
        String response = "";
        switch (action) {
        case ("bye"):
            response = "bye";
            break;
        case ("list"):
            response = taskList.display("Here are the tasks in your list:");
            break;
        case ("done"):
            response = getResponseForDone(input);
            storage.saveData(taskList);
            break;
        case ("delete"):
            response = getResponseForDelete(input);
            storage.saveData(taskList);
            break;
        case ("todo"):
            response = getResponseForTodo(input);
            storage.saveData(taskList);
            break;
        case ("deadline"):
            response = getResponseForDeadline(input);
            storage.saveData(taskList);
            break;
        case ("event"):
            response = getResponseForEvent(input);
            storage.saveData(taskList);
            break;
        case ("find"):
            response = getResponseForFind(input);
            break;
        case ("remind"):
            response = getResponseForRemind(input);
            break;
        default:
            assert false;
            throw getResponseForUnknown();
        }
        return response;
    }

    /**
     * Returns the response for "done" case.
     *
     * @param input the input by the user.
     * @return the response for "done".
     * @throws DukeException is thrown when no taskNumber is indicated,
     * taskNumber is not an integer or taskNumber is greater than the
     * number of tasks.
     */
    private String getResponseForDone(String input) throws DukeException {
        String[] info = input.split(" ", 2);
        if (info.length == 1) {
            throw new DukeException(":( OOPS!!! "
                    + "Please enter the task number you would like to mark as done.");
        }
        int taskNumber;
        try {
            taskNumber = Integer.parseInt(info[1]);
        } catch (NumberFormatException e) {
            throw new DukeException(":( OOPS!!! "
                    + "Please enter an integer for the task you would like to mark as done.");
        }
        if (taskNumber > taskList.getSize()) {
            throw new DukeException(":( OOPS!!! "
                    + "There isn't a task number " + taskNumber + ".");
        }
        return taskList.markAsDone(taskNumber);
    }

    /**
     * Returns the response for "delete" case.
     *
     * @param input the input by the user.
     * @return the response for "delete".
     * @throws DukeException is thrown when no taskNumber is indicated,
     * taskNumber is not an integer or taskNumber is greater than the
     * number of tasks.
     */
    private String getResponseForDelete(String input) throws DukeException {
        String[] info = input.split(" ", 2);
        if (info.length == 1) {
            throw new DukeException(":( OOPS!!! "
                    + "Please enter the task number you would like to delete.");
        }
        int taskNumber;
        try {
            taskNumber = Integer.parseInt(info[1]);
        } catch (NumberFormatException e) {
            throw new DukeException(":( OOPS!!! "
                    + "Please enter an integer for the task you would like to delete.");
        }
        if (taskNumber > taskList.getSize()) {
            throw new DukeException(":( OOPS!!! "
                    + "There isn't a task number " + taskNumber + ".");
        }
        return taskList.delete(taskNumber);
    }

    /**
     * Returns the response for "todo" case.
     *
     * @param input the input by the user.
     * @return the response for "todo".
     * @throws DukeException is thrown when no description is provided.
     */
    private String getResponseForTodo(String input) throws DukeException {
        String[] info = input.split(" ", 2);
        if (info.length == 1) {
            throw new DukeException(":( OOPS!!! "
                    + "The description of a todo cannot be empty.");
        }
        Todo newTodo = new Todo(info[1]);
        return taskList.add(newTodo);
    }

    /**
     * Returns the response for "deadline" case.
     *
     * @param input the input by the user.
     * @return the response for "deadline".
     * @throws DukeException is thrown when no description is provided,
     * no deadline is provided, or a wrong date or time format is provided.
     */
    private String getResponseForDeadline(String input) throws DukeException {
        String[] info = input.split(" ", 2);
        String response = "";
        if (info.length == 1) {
            throw new DukeException(":( OOPS!!! "
                    + "The description of a deadline cannot be empty.");
        }
        String[] description = info[1].split("/by ", 2);
        if (description.length == 1) {
            throw new DukeException(":( OOPS!!! "
                    + "The deadline of a deadline cannot be empty.");
        }
        String[] dateTime = description[1].split(" ", 2);
        LocalDate date;
        LocalTime time;
        Deadline newDeadline;
        try {
            date = LocalDate.parse(dateTime[0]);
        } catch (DateTimeParseException e) {
            throw new DukeException(":( OOPS!!! "
                    + "The format of the date must be in yyyy-mm-dd.");
        }
        if (dateTime.length == 1) {
            newDeadline = new Deadline(description[0], date);
            response = taskList.add(newDeadline);
        } else if (dateTime.length == 2) {
            try {
                time = LocalTime.parse(dateTime[1]);
            } catch (DateTimeParseException e) {
                throw new DukeException(":( OOPS!!! "
                        + "The format of the time must be in hh:mm.");
            }
            newDeadline = new Deadline(description[0], date, time);
            response = taskList.add(newDeadline);
        } else {
            assert false;
        }
        return response;
    }

    /**
     * Returns the response for "event" case.
     *
     * @param input the input by the user.
     * @return the response for "event".
     * @throws DukeException is thrown when no description is provided,
     * no date is provided, or a wrong date or time format is provided.
     */
    private String getResponseForEvent(String input) throws DukeException {
        String[] info = input.split(" ", 2);
        String response = "";
        if (info.length == 1) {
            throw new DukeException(":( OOPS!!! "
                    + "The description of an event cannot be empty.");
        }
        String[] description = info[1].split("/at ", 2);
        if (description.length == 1) {
            throw new DukeException(":( OOPS!!! "
                    + "The date of an event cannot be empty.");
        }
        String[] dateTime = description[1].split(" ", 2);
        LocalDate date;
        LocalTime time;
        Event newEvent;
        try {
            date = LocalDate.parse(dateTime[0]);
        } catch (DateTimeParseException e) {
            throw new DukeException(":( OOPS!!! "
                    + "The format of the date must be in yyyy-mm-dd.");
        }
        if (dateTime.length == 1) {
            newEvent = new Event(description[0], date);
            response = taskList.add(newEvent);
        } else if (dateTime.length == 2) {
            try {
                time = LocalTime.parse(dateTime[1]);
            } catch (DateTimeParseException e) {
                throw new DukeException(":( OOPS!!! "
                        + "The format of the time must be in hh:mm.");
            }
            newEvent = new Event(description[0], date, time);
            response = taskList.add(newEvent);
        } else {
            assert false;
        }
        return response;
    }

    /**
     * Returns the response for "find" case.
     *
     * @param input the input by the user.
     * @return the response for "find".
     * @throws DukeException is thrown when no keyword is provided.
     */
    private String getResponseForFind(String input) throws DukeException {
        String[] searchInfo = input.split(" ", 2);
        if (searchInfo.length == 1) {
            throw new DukeException(":( OOPS!!! "
                    + "Please include a keyword for your search.");
        }
        return taskList.findTasks(searchInfo[1]);
    }

    /**
     * Returns the response for "remind" case.
     *
     * @param input the input by the user.
     * @return the response for "remind".
     * @throws DukeException is thrown when no period is provided.
     */
    private String getResponseForRemind(String input) throws DukeException {
        String[] reminderInfo = input.split(" ", 2);
        String result = "";
        if (reminderInfo.length == 1) {
            throw new DukeException(":( OOPS!!! "
                    + "Please include a period for your reminder.");
        }
        boolean isValidInput = reminderInfo[1].equals("today")
                || reminderInfo[1].equals("tomorrow")
                || reminderInfo[1].equals("week")
                || reminderInfo[1].equals("month");
        if (isValidInput) {
            result = taskList.getTasksForReminder(reminderInfo[1]);
        } else {
            throw new DukeException(":( OOPS!!! "
                    + "I can only show reminder for 'today', 'tomorrow', 'week' or 'month'.");
        }
        return result;
    }

    /**
     * Returns the DukeException for an Unknown command case.
     *
     * @return the DukeException for an Unknown command case.
     */
    private DukeException getResponseForUnknown() {
        return new DukeException(":( OOPS!!! "
                + "I'm sorry, but I don't know what that means :-(");
    }
}
