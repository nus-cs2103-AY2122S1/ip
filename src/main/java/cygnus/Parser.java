package cygnus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import cygnus.task.Deadline;
import cygnus.task.Event;
import cygnus.task.Task;
import cygnus.task.ToDo;

/**
 * Represents the text parser for Cygnus. When parsing a user input, the parser
 * also handles the updating of the associated TaskList.
 *
 * @author Joshua Yong
 */
public class Parser {

    private static final String dateTimeInputFormat = "ddMMyy HHmm";
    private static final String dateInputFormat = "ddMMyy";

    /**
     * The TaskList which the Parser updates.
     */
    private final TaskList tasks;

    /**
     * Class constructor.
     *
     * @param tasks The TaskList to be updated during parsing.
     */
    public Parser(TaskList tasks) {
        assert tasks != null : "TaskList passed into Parser constructor is not initialized";
        this.tasks = tasks;
    }

    /**
     * Parses the user input String, updating the associated TaskList.
     * Also returns an appropriate String to be displayed to the user.
     *
     * @param userInput The String inputted by the user.
     * @return A String to be displayed to the user.
     * @throws CygnusException If the user input is invalid.
     */
    public String parse(String userInput) throws CygnusException {
        String[] inputStringArray = userInput.split(" ", 2);
        String firstWordOfInput = inputStringArray[0];
        if (inputStringArray.length == 1) {
            switch (firstWordOfInput) {
            case "help":
                return "Refer to https://jyrw.github.io/ip/ for a full list of commands.";
            case "list":
                return tasks.getAllTasksString();
            case "done":
                // Fallthrough
            case "delete":
                throw new CygnusException("Please specify a task number.");
            case "find":
                throw new CygnusException("Please specify a keyword.");
            case "upcoming":
                return tasks.getUpcomingDeadlinesString();
            case "deadline":
                // Fallthrough
            case "event":
                // Fallthrough
            case "todo":
                throw new CygnusException("Please specify the task info.");
            default:
                throw new CygnusException("Sorry, I don't know what that means.");
            }
        } else {
            String restOfInput = inputStringArray[1];
            switch (firstWordOfInput) {
            case "done":
                try {
                    int taskIndex = Integer.parseInt(restOfInput) - 1;
                    tasks.setTaskAsDone(taskIndex);
                    return "Nice! I've marked this task as done:\n "
                            + tasks.getTask(taskIndex).toString();
                } catch (NumberFormatException | IndexOutOfBoundsException e) {
                    throw new CygnusException("Please specify a valid task number.");
                }
            case "delete":
                try {
                    int taskIndex = Integer.parseInt(restOfInput) - 1;
                    Task deletedTask = tasks.getTask(taskIndex);
                    tasks.deleteTask(taskIndex);
                    return "Noted. I've deleted this task:\n " + deletedTask.toString();
                } catch (NumberFormatException | IndexOutOfBoundsException e) {
                    throw new CygnusException("Please specify a valid task number.");
                }
            case "find":
                return tasks.getMatchingTasksString(restOfInput);
            case "deadline":
                Deadline newDeadline = createDeadline(restOfInput);
                tasks.addTask(newDeadline);
                return "Got it. I've added this task:\n " + newDeadline.toString();
            case "event":
                Event newEvent = createEvent(restOfInput);
                tasks.addTask(newEvent);
                return "Got it. I've added this task:\n " + newEvent.toString();
            case "todo":
                Task newToDo = createToDo(restOfInput);
                tasks.addTask(newToDo);
                return "Got it. I've added this task:\n " + newToDo.toString();
            default:
                throw new CygnusException("Sorry, I don't know what that means.");
            }
        }
    }

    private Deadline createDeadline(String taskInfo) throws CygnusException {
        String[] deadlineInfo = taskInfo.split(" /by ", 2);
        if (deadlineInfo.length < 2) {
            throw new CygnusException("Please enter a valid deadline format.");
        }

        try {
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(dateInputFormat);
            LocalDate by = LocalDate.parse(deadlineInfo[1], dateFormatter);
            return new Deadline(deadlineInfo[0], by);
        } catch (DateTimeParseException e) {
            throw new CygnusException("Please enter a valid date with format " + dateInputFormat + ".");
        }
    }

    private Event createEvent(String taskInfo) throws CygnusException {
        String[] eventInfo = taskInfo.split(" /at ", 2);
        if (eventInfo.length < 2) {
            throw new CygnusException("Please enter a valid event format.");
        }

        try {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(dateTimeInputFormat);
            LocalDateTime at = LocalDateTime.parse(eventInfo[1], dateTimeFormatter);
            return new Event(eventInfo[0], at);
        } catch (DateTimeParseException e) {
            throw new CygnusException("Please enter a valid date with format " + dateTimeInputFormat + ".");
        }
    }

    private ToDo createToDo(String taskInfo) {
        return new ToDo(taskInfo);
    }

}
