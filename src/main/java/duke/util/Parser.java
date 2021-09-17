package duke.util;

import static java.lang.Integer.parseInt;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.InvalidCommand;
import duke.command.ListCommand;
import duke.command.RemindCommand;
import duke.exception.EmptyDescriptionException;
import duke.exception.InvalidArgumentException;
import duke.exception.InvalidDateInputException;
import duke.exception.InvalidTimeInputException;
import duke.exception.MissingArgumentException;
import duke.exception.MissingIndexException;

/**
 * Class to deal with making sense of the user's inputted command.
 *
 * @author Benedict Chua
 */
public class Parser {
    private TaskList tasks;

    public Parser(TaskList taskList) {
        this.tasks = taskList;
    }

    /**
     * Returns the Command based on the first keyword from the full command (input) given by the user,.
     * Command will contain relevant info from the rest of the input if applicable.
     *
     * @param input the String that the user enters into Duke.
     * @return the corresponding Command based from the input.
     */
    public Command getCommand(String input) {
        switch (input.split(" ")[0]) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand(this.tasks, "all", null);
        case "check":
            return new ListCommand(this.tasks, "date", getTaskDescription(input));
        case "find":
            return new ListCommand(this.tasks, "keyword", getTaskDescription(input));
        case "remind":
            return new RemindCommand(this.tasks, getRemindArgument(input));
        case "done":
            return new DoneCommand(this.tasks, getAllTaskIndexes(input));
        case "todo":
            return new AddCommand(this.tasks, getTaskDescription(input), "ToDo");
        case "deadline":
            return new AddCommand(this.tasks, getTaskDescription(input), "Deadline");
        case "event":
            return new AddCommand(this.tasks, getTaskDescription(input), "Event");
        case "delete":
            return new DeleteCommand(this.tasks, getTaskIndex(input));
        default:
            return new InvalidCommand(input);
        }
    }

    /**
     * Takes in the original inputted command and filters it for the task description.
     * The description is the command string minus the first word which is the command itself.
     *
     * @param command the original command inputted by the user.
     * @return the filtered String that contains only the description of the task.
     * @throws EmptyDescriptionException when description is missing (including if it contains only white space).
     */
    public static String getTaskDescription(String command) throws EmptyDescriptionException {
        String[] commandItems = command.split(" ", 2);
        if (commandItems.length == 1) {
            throw new EmptyDescriptionException();
        }

        String filteredDescription = commandItems[1];
        if (filteredDescription.trim().isEmpty()) {
            throw new EmptyDescriptionException();
        }

        return filteredDescription.trim();
    }

    /**
     * Takes in the original inputted command for a reminder and filters it for the command argument.
     * The argument is the command string minus the first word which is the command itself.
     *
     * @param command the original command inputted by the user.
     * @return the filtered String that contains the reminder argument.
     * @throws InvalidArgumentException when description is missing (including if it contains only white space).
     */
    public static String getRemindArgument(String command) throws InvalidArgumentException {
        String[] commandItems = command.split(" ", 2);
        if (commandItems.length == 1) {
            throw new InvalidArgumentException("empty");
        }

        String argument = commandItems[1].trim();
        if (argument.isEmpty()) {
            throw new InvalidArgumentException(argument);
        }

        if (!argument.matches("today|week|next")) {
            throw new InvalidArgumentException(argument);
        }

        return argument;
    }

    /**
     * Takes in the original inputted command and filters and parses it for the given integer index.
     * The index is the full command string minus the first word which is the command itself.
     *
     * @param command the original command inputted by the user.
     * @return the filtered and parsed int that refers to the index of the task.
     * @throws InvalidArgumentException when index given (in String) is invalid.
     * @throws MissingIndexException when index is missing (including if it contains only white space).
     */
    public static int getTaskIndex(String command) throws InvalidArgumentException, MissingIndexException {
        String[] commandItems = command.split(" ", 2);
        if (commandItems.length == 1) {
            throw new MissingIndexException();
        }

        String indexString = commandItems[1];
        if (indexString.trim().isEmpty()) {
            throw new MissingIndexException();
        }

        try {
            return parseInt(indexString);
        } catch (NumberFormatException e) {
            throw new InvalidArgumentException(indexString);
        }
    }

    /**
     * Takes in the original inputted command and filters and parses it for the given integer index.
     * The index is the full command string minus the first word which is the command itself.
     *
     * @param command the original command inputted by the user.
     * @return the filtered and parsed int that refers to the index of the task.
     * @throws InvalidArgumentException when index given (in String) is invalid.
     * @throws MissingIndexException when index is missing (including if it contains only white space).
     */
    public static int[] getAllTaskIndexes(String command) throws InvalidArgumentException, MissingIndexException {
        ArrayList<Integer> indexes = new ArrayList<>();

        // Do checks
        String[] commandItems = command.split(" ", 2);
        if (commandItems.length == 1) {
            throw new MissingIndexException();
        }
        String indexString = commandItems[1];
        if (indexString.trim().isEmpty()) {
            throw new MissingIndexException();
        }

        // Store all valid indexes
        String[] stringIndexes = command.split(" ");

        for (int i = 1; i < stringIndexes.length; i++) {
            if (!stringIndexes[i].isEmpty()) {
                try {
                    indexes.add(parseInt(stringIndexes[i]));
                } catch (NumberFormatException e) {
                    throw new InvalidArgumentException(stringIndexes[i]);
                }
            }
        }

        return indexes.stream().mapToInt(Integer::intValue).toArray();
    }

    /**
     * Takes in a date in the form of a string and parses it to return a LocalDate object.
     * The date is recognised if given in the form dd/mm/yyyy, dd-mm-yyyy or yyyy-mm-dd.
     *
     * @param dateString String containing the date in the form dd/mm/yyyy, dd-mm-yyyy or yyyy-mm-dd.
     * @return LocalDate that is parsed from the given dateString.
     * @throws InvalidDateInputException if date is in the wrong form or an invalid date is given.
     */
    public static LocalDate parseDate(String dateString) throws InvalidDateInputException {
        try {
            if (dateString.matches("\\d{2}-\\d{2}-\\d{4}")) {
                // in the form dd-mm-yyyy
                String[] d = dateString.split("-", 3);
                return LocalDate.parse(String.format("%s-%s-%s", d[2], d[1], d[0]));
            } else if (dateString.matches("\\d{2}/\\d{2}/\\d{4}")) {
                // in the form dd/mm/yyyy
                String[] d = dateString.split("/", 3);
                return LocalDate.parse(String.format("%s-%s-%s", d[2], d[1], d[0]));
            } else if (dateString.matches("\\d{4}-\\d{2}-\\d{2}")) {
                // in the form yyyy-mm-dd
                return LocalDate.parse(dateString);
            } else {
                throw new InvalidDateInputException(dateString);
            }
        } catch (DateTimeException e) {
            throw new InvalidDateInputException(dateString);
        }
    }

    /**
     * Takes in a time in the form of a string and parses it to return a LocalTime object.
     * The time is recognised if it is in the 24-hour format and given in the form hhmm or hh:mm.
     *
     * @param timeString String containing the time (in 24hr format) in the form hhmm or hh:mm.
     * @return LocalTime that is parsed from the given timeString.
     * @throws InvalidTimeInputException if time is in the wrong form or an invalid time is given.
     */
    public static LocalTime parseTime(String timeString) throws InvalidTimeInputException {
        try {
            if (timeString.indexOf(':') == -1 && timeString.length() == 4) {
                // in the form hhmm
                String[] t = {timeString.substring(0, 2), timeString.substring(2)};
                return LocalTime.parse(String.format("%s:%s", t[0], t[1]));
            } else if (timeString.indexOf(':') == 2 && timeString.length() == 5) {
                //in the form hh:mm
                return LocalTime.parse(timeString);
            } else {
                throw new InvalidTimeInputException(timeString);
            }
        } catch (DateTimeException e) {
            throw new InvalidTimeInputException(timeString);
        }
    }

    /**
     * Parses the actual task description and date & time given from a full Deadline task description.
     *
     * @param task full description for the given Deadline task.
     * @return String[] containing the actual task description and date & time
     * @throws MissingArgumentException if description is missing the /by argument
     */
    public static String[] parseDeadlineDate(String task) throws MissingArgumentException {
        String[] deadlineDetails = task.split(" /by ");
        if (deadlineDetails.length == 1) {
            throw new MissingArgumentException("'/by'", "Deadline");
        }

        return deadlineDetails;
    }

    /**
     * Parses the actual task description and date & time given from a full Event task description.
     *
     * @param task full description for the given Eveent task.
     * @return String[] containing the actual task description and date & time
     * @throws MissingArgumentException if description is missing the /at argument
     */
    public static String[] parseEventDate(String task) throws MissingArgumentException {
        String[] eventDetails = task.split(" /at ");
        if (eventDetails.length == 1) {
            throw new MissingArgumentException("'/at'", "Event");
        }

        return eventDetails;
    }
}
