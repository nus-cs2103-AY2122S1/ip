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
     * Handles: bye, list, done, delete, todo, deadline, event, find commands.
     *
     * @param input the user input from the Ui.
     * @return a boolean that determines if Duke is still inSession.
     * @throws DukeException is thrown when the input is invalid or unknown.
     */
    public boolean parseCommand(String input) throws DukeException {
        String action = input.split(" ", 2)[0].toLowerCase();
        switch (action) {
        case ("bye"):
            return false;
        case ("list"):
            taskList.display("Here are the tasks in your list:");
            break;
        case ("done"): {
            String[] info = input.split(" ", 2);
            if (info.length == 1) {
                throw new DukeException("☹ OOPS!!! "
                        + "Please enter the task number you would like to mark as done.");
            }
            int taskNumber;
            try {
                taskNumber = Integer.parseInt(info[1]);
            } catch (NumberFormatException e) {
                throw new DukeException("☹ OOPS!!! "
                        + "Please enter an integer for the task you would like to mark as done.");
            }
            taskList.markAsDone(taskNumber);
            storage.saveData(taskList);
        }
            break;
        case ("delete"): {
            String[] info = input.split(" ", 2);
            if (info.length == 1) {
                throw new DukeException("☹ OOPS!!! "
                        + "Please enter the task number you would like to delete.");
            }
            int taskNumber;
            try {
                taskNumber = Integer.parseInt(info[1]);
            } catch (NumberFormatException e) {
                throw new DukeException("☹ OOPS!!! "
                        + "Please enter an integer for the task you would like to delete.");
            }
            taskList.delete(taskNumber);
            storage.saveData(taskList);
        }
            break;
        case ("todo"): {
            String[] info = input.split(" ", 2);
            if (info.length == 1) {
                throw new DukeException("☹ OOPS!!! "
                        + "The description of a todo cannot be empty.");
            }
            Todo newTodo = new Todo(info[1]);
            taskList.add(newTodo);
            storage.saveData(taskList);
        }
            break;
        case ("deadline"): {
            String[] info = input.split(" ", 2);
            if (info.length == 1) {
                throw new DukeException("☹ OOPS!!! "
                        + "The description of a deadline cannot be empty.");
            }
            String[] description = info[1].split("/by ", 2);
            if (description.length == 1) {
                throw new DukeException("☹ OOPS!!! "
                        + "The deadline of a deadline cannot be empty.");
            }
            String[] dateTime = description[1].split(" ", 2);
            LocalDate date;
            LocalTime time;
            Deadline newDeadline;
            try {
                date = LocalDate.parse(dateTime[0]);
            } catch (DateTimeParseException e) {
                throw new DukeException("☹ OOPS!!! "
                        + "The format of the date must be in yyyy-mm-dd.");
            }
            if (dateTime.length == 1) {
                newDeadline = new Deadline(description[0], date);
            } else {
                try {
                    time = LocalTime.parse(dateTime[1]);
                } catch (DateTimeParseException e) {
                    throw new DukeException("☹ OOPS!!! "
                            + "The format of the time must be in hh:mm.");
                }
                newDeadline = new Deadline(description[0], date, time);
            }
            taskList.add(newDeadline);
            storage.saveData(taskList);
        }
            break;
        case ("event"):
            String[] info = input.split(" ", 2);
            if (info.length == 1) {
                throw new DukeException("☹ OOPS!!! "
                        + "The description of an event cannot be empty.");
            }
            String[] description = info[1].split("/at ", 2);
            if (description.length == 1) {
                throw new DukeException("☹ OOPS!!! "
                        + "The date of an event cannot be empty.");
            }
            String[] dateTime = description[1].split(" ", 2);
            LocalDate date;
            LocalTime time;
            Event newEvent;
            try {
                date = LocalDate.parse(dateTime[0]);
            } catch (DateTimeParseException e) {
                throw new DukeException("☹ OOPS!!! "
                        + "The format of the date must be in yyyy-mm-dd.");
            }
            if (dateTime.length == 1) {
                newEvent = new Event(description[0], date);
            } else {
                try {
                    time = LocalTime.parse(dateTime[1]);
                } catch (DateTimeParseException e) {
                    throw new DukeException("☹ OOPS!!! "
                            + "The format of the time must be in hh:mm.");
                }
                newEvent = new Event(description[0], date, time);
            }
            taskList.add(newEvent);
            storage.saveData(taskList);
            break;
        case ("find"):
            String[] searchInfo = input.split(" ", 2);
            if (searchInfo.length == 1) {
                throw new DukeException("☹ OOPS!!! "
                        + "Please include a keyword for your search.");
            }
            taskList.findTasks(searchInfo[1]);
            break;
        default:
            throw new DukeException("☹ OOPS!!! "
                    + "I'm sorry, but I don't know what that means :-(");
        }
        return true;
    }
}
