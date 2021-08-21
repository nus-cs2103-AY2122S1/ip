import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;

import static java.lang.Integer.parseInt;

public class Parser {
    private TaskList taskList;

    public Parser(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * From the input given by the user, returns the Command (first keyword) in it.
     *
     * @param input the String that the user enters into Duke.
     * @return the corresponding Command in the input.
     */
    public Command getCommand(String input) {
        switch (input.split(" ")[0]) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand(this.taskList, null);
        case "check":
            return new ListCommand(this.taskList, filterTaskDescription(input));
        case "done":
            return new DoneCommand(this.taskList, filterTaskIndex(input));
        case "todo":
            return new AddCommand(this.taskList, filterTaskDescription(input), "ToDo");
        case "deadline":
            return new AddCommand(this.taskList, filterTaskDescription(input), "Deadline");
        case "event":
            return new AddCommand(this.taskList, filterTaskDescription(input), "Event");
        case "delete":
            return new DeleteCommand(this.taskList, filterTaskIndex(input));
        default:
            return new InvalidCommand(input);
        }
    }

    /**
     * Takes in the original inputted command and filters it for the task description.
     *
     * The description is the command string minus the first word which is the command itself.
     * Throws an EmptyDescriptionException when description is missing (including if it contains only white space).
     *
     * @param command the original command inputted by the user.
     * @return the filtered String that contains only the description of the task.
     * @throws EmptyDescriptionException when description is missing
     */
    public static String filterTaskDescription(String command) throws EmptyDescriptionException {
        String[] commandItems = command.split(" ", 2);
        if (commandItems.length == 1) {
            throw new EmptyDescriptionException();
        }
        String filteredDescription  = command.split(" ", 2)[1];
        if (filteredDescription.trim().isEmpty()) {
            throw new EmptyDescriptionException();
        }
        return filteredDescription.trim();
    }

    /**
     * Takes in the original inputted command and filters and parses it for the given index.
     *
     * The index is the command string minus the first word which is the command itself.
     * Throws an MissingIndexException when index is missing (including if it contains only white space).
     *
     * @param command the original command inputted by the user.
     * @return the filtered and parsed int that refers to the index of the task.
     * @throws MissingIndexException when index is missing
     */
    public static int filterTaskIndex(String command) throws MissingIndexException {
        String[] commandItems = command.split(" ", 2);
        if (commandItems.length == 1) {
            throw new MissingIndexException();
        }
        String indexString  = command.split(" ", 2)[1];
        if (indexString.trim().isEmpty()) {
            throw new MissingIndexException();
        }
        return parseInt(indexString);
    }

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

    public static LocalTime parseTime(String timeString) throws InvalidTimeInputException {
        try {
            if (timeString.indexOf(':')  == -1 && timeString.length() == 4) {
                // in the form hhmm
                String[] t = {timeString.substring(0, 2), timeString.substring(2)};
                return LocalTime.parse(String.format("%s:%s", t[0], t[1]));
            } else if (timeString.indexOf(':')  == 2 && timeString.length() == 5) {
                //in the form hh:mm
                return LocalTime.parse(timeString);
            } else {
                // Maybe throw exception
                throw new InvalidTimeInputException(timeString);
            }
        } catch (DateTimeException e) {
            throw new InvalidTimeInputException(timeString);
        }
    }

    public static String[] parseDeadlineDate(String task) throws MissingArgumentException {
        String[] deadlineDetails = task.split(" /by ");
        if (deadlineDetails.length == 1) {
            throw new MissingArgumentException("'/by'", "Deadline");
        }

        return deadlineDetails;
    }

    public static String[] parseEventDate(String task) throws MissingArgumentException {
        String[] eventDetails = task.split(" /at ");
        if (eventDetails.length == 1) {
            throw new MissingArgumentException("'/at'", "Event");
        }

        return eventDetails;
    }
}