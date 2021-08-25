package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Encapsulates a Parser, which parses user inputs, interprets them as commands and
 * updates the TaskList.
 *
 * @author Hanif Kamal
 */
public class Parser {
    /** The TaskList that the Parser updates after command interpretation */
    private TaskList list;

    /**
     * Class constructor to initialize a Parser instance.
     *
     * @param list The TaskList that is to be updated.
     */
    public Parser(TaskList list) {
        this.list = list;
    }

    /**
     * Takes in a String which represents user input, and parses it. It executes the
     * interpreted commands and then updates the TaskList accordingly.
     *
     * @param input The String that is input by the user.
     * @throws DukeException In the case of invalid inputs.
     */
    public void parse(String input) throws DukeException {
        String[] splitInput = input.split(" ", 2);
        String first = splitInput[0];
        if (splitInput.length == 1) {
            switch (first) {
            case ("list"):
                list.printTasks();
                break;
            case ("done"):
                throw new DukeException("Please ensure that there is a number after the command 'done'. Try again.");
            case ("deadline"):
                throw new DukeException("Please ensure that there is a task description after the command 'deadline' " +
                        "and a deadline after '/by'. Try again.");
            case ("event"):
                throw new DukeException("Please ensure that there is a task description after the command 'deadline' " +
                        "with a date and time after '/at'. Try again.");
            case ("todo"):
                throw new DukeException("Please ensure that there is a task description after the command 'todo'. " +
                        "Try again.");
            case ("delete"):
                throw new DukeException("Please ensure that there is a number after the command 'delete'. Try again.");
            case ("find"):
                throw new DukeException("Please ensure that there is a search term after the command 'find'. " +
                        "Try again.");
            default:
                throw new DukeException("I didn't quite get what you meant. To add a task, begin with " +
                        "'deadline', 'event' or 'todo'.");
            }
        } else {
            switch (first) {
            case ("done"):
                try {
                    String listIndexString = splitInput[1];
                    int listIndex = Integer.parseInt(listIndexString);
                    if (listIndex <= list.size() && listIndex >= 1) {
                        list.completeTask(listIndex);
                        break;
                    } else {
                        throw new DukeException("Please ensure that a valid number follows the command 'done'. " +
                                "Try again.");
                    }
                } catch (NumberFormatException e) {
                    throw new DukeException("Please ensure only a number follows the command 'done'. Try again.");
                }
            case ("deadline"):
                String restOfDeadline = splitInput[1];
                if (!restOfDeadline.contains("/by")) {
                    throw new DukeException("Please state the deadline for this task with /by! Try again.");
                } else {
                    try {
                        String[] restOfDeadlineSplit = restOfDeadline.split(" /by ", 2);
                        if (restOfDeadlineSplit.length < 2) {
                            throw new DukeException("Please ensure that there is a task description and deadline. " +
                                    "Try again");
                        }
                        String deadlineDescription = restOfDeadlineSplit[0];
                        String deadlineDateString = restOfDeadlineSplit[1];
                        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yy");
                        LocalDate deadlineDate = LocalDate.parse(deadlineDateString, dtf);
                        Deadline createdDeadlineTask = new Deadline(deadlineDescription, false, deadlineDate);
                        list.addToList(createdDeadlineTask);
                        System.out.println("Got it. I've added this task:\n" + "  " + createdDeadlineTask + "\n"
                                + "Now you have " + list.size() + " task" + (list.size() == 1 ? "" : "s") +
                                " in the list.");
                        break;
                    } catch (DateTimeParseException e) {
                        throw new DukeException("Please ensure that your deadline is formatted in the following " +
                                "way: DD/MM/YY");
                    }
                }
            case ("event"):
                String restOfEvent = splitInput[1];
                if (!restOfEvent.contains("/at")) {
                    throw new DukeException("Please state the date and time for this task with /at! Try again.");
                } else {
                    try {
                        String[] restOfEventSplit = restOfEvent.split(" /at ", 2);
                        if (restOfEventSplit.length < 2) {
                            throw new DukeException("Please ensure that there is a task description with a date and " +
                                    "time. Try again");
                        }
                        String eventDescription = restOfEventSplit[0];
                        String eventDateTimeString = restOfEventSplit[1];
                        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yy HHmm");
                        LocalDateTime eventDateTime = LocalDateTime.parse(eventDateTimeString, dtf);
                        Event createdEventTask = new Event(eventDescription, false, eventDateTime);
                        list.addToList(createdEventTask);
                        System.out.println("Got it. I've added this task:\n" + "  " + createdEventTask + "\n"
                                + "Now you have " + list.size() + " task" + (list.size() == 1 ? "" : "s") +
                                " in the list.");
                        break;
                    } catch (DateTimeParseException e) {
                        throw new DukeException("Please ensure that your deadline is formatted in the following " +
                                "way: DD/MM/YY");
                    }
                }
            case ("todo"):
                String todoDescription = splitInput[1];
                Todo createdTodoTask = new Todo(todoDescription, false);
                list.addToList(createdTodoTask);
                System.out.println("Got it. I've added this task:\n" + "  " + createdTodoTask + "\n"
                        + "Now you have " + list.size() + " task" + (list.size() == 1 ? "" : "s") +
                        " in the list.");
                break;
            case ("delete"):
                String toDeleteIndexString = splitInput[1];
                try {
                    int toDeleteIndex = Integer.parseInt(toDeleteIndexString);
                    list.deleteTask(toDeleteIndex);
                    break;
                } catch (NumberFormatException e) {
                    throw new DukeException("Please make sure only a number follows the command 'delete'. " +
                            "Try again.");
                } catch (StringIndexOutOfBoundsException e) {
                    throw new DukeException("Please add a number after the command 'delete'. Try again.");
                }
            case ("find"):
                String searchTerm = splitInput[1];
                list.printFilteredTasks(searchTerm);
                break;
            default:
                throw new DukeException("I didn't quite get what you meant. To add a task, begin with " +
                        "'deadline', 'event' or 'todo'.");
            }
        }
    }
}