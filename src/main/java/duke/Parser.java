package duke;

import duke.task.Task;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents the text parser for Duke. When parsing a user input, the parser
 * also handles the updating of the associated TaskList.
 *
 * @author Joshua Yong
 */
public class Parser {

    // The TaskList which the Parser updates
    private TaskList tasks;

    /**
     * Class constructor.
     *
     * @param tasks The TaskList to be updated during parsing.
     */
    public Parser(TaskList tasks) {
        this.tasks = tasks;
    }

    /**
     * Parses the user input String, updating the associated TaskList.
     * Also returns an appropriate String to be displayed to the user.
     *
     * @param userInput The String inputted by the user.
     * @return A String to be displayed to the user.
     * @throws DukeException if the user input is invalid.
     */
    public String parse(String userInput) throws DukeException {
        String[] inputStringArray = userInput.split(" ", 2);
        String firstWord = inputStringArray[0];
        if (inputStringArray.length == 1) {
            switch (firstWord) {
            case "list":
                return "Here are the tasks in your list:" + getTasksString();
            case "done":
                // Fallthrough
            case "delete":
                throw new DukeException("Please specify a task number.");
            case "deadline":
                // Fallthrough
            case "event":
                // Fallthrough
            case "todo":
                throw new DukeException("Please specify the task info.");
            default:
                throw new DukeException("Sorry, I don't know what that means.");
            }
        } else {
            switch (firstWord) {
            case "done":
                try {
                    int taskIndex = Integer.parseInt(inputStringArray[1]) - 1;
                    tasks.setTaskAsDone(taskIndex);
                    return "Nice! I've marked this task as done:\n "
                            + tasks.getTask(taskIndex).toString();
                } catch (NumberFormatException | IndexOutOfBoundsException e) {
                    throw new DukeException("Please specify a valid task number.");
                }
            case "delete":
                try {
                    int taskIndex = Integer.parseInt(inputStringArray[1]) - 1;
                    Task deletedTask = tasks.getTask(taskIndex);
                    tasks.deleteTask(taskIndex);
                    return "Noted. I've removed this task:\n "
                            + deletedTask.toString();
                } catch (NumberFormatException | IndexOutOfBoundsException e) {
                    throw new DukeException("Please specify a valid task number.");
                }
            case "deadline":
                String[] deadlineInfo = inputStringArray[1].split(" /by ", 2);
                if (deadlineInfo.length < 2) {
                    throw new DukeException("Please enter a valid deadline format.");
                }
                try {
                    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("ddMMyy");
                    LocalDate by = LocalDate.parse(deadlineInfo[1], dateFormatter);
                    Task newDeadline = new Deadline(deadlineInfo[0], by);
                    tasks.addTask(newDeadline);
                    return "Got it. I've added this task:\n "
                            + newDeadline.toString();
                } catch (DateTimeParseException e) {
                    throw new DukeException("Please enter a valid date.");
                }
            case "event":
                String[] eventInfo = inputStringArray[1].split(" /at ", 2);
                if (eventInfo.length < 2) {
                    throw new DukeException("Please enter a valid event format.");
                }
                try {
                    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("ddMMyy HHmm");
                    LocalDateTime at = LocalDateTime.parse(eventInfo[1], dateTimeFormatter);
                    Task newEvent = new Event(eventInfo[0], at);
                    tasks.addTask(newEvent);
                    return "Got it. I've added this task:\n " +
                            newEvent.toString();
                } catch (DateTimeParseException e) {
                    throw new DukeException("Please enter a valid date.");
                }
            case "todo":
                Task newToDo = new ToDo(inputStringArray[1]);
                tasks.addTask(newToDo);
                return "Got it. I've added this task:\n " +
                        newToDo.toString();
            default:
                throw new DukeException("Sorry, I don't know what that means.");
            }
        }
    }

    /**
     * Outputs the associated TaskList as a formatted String.
     *
     * @return A formatted String representation of the associated TaskList.
     */
    private String getTasksString() { // TODO: Change to StringBuilder, put in TaskList
        String taskString = "";
        int taskCount = tasks.getSize();
        for (int i = 0; i < taskCount; i++) {
            taskString += "\n " + (i + 1) + ". " + tasks.getTask(i).toString();
        }
        return taskString;
    }

}
