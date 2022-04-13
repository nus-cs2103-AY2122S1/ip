package duck;

import duck.command.AddCommand;
import duck.command.ByeCommand;
import duck.command.Command;
import duck.command.DeleteCommand;
import duck.command.DoneCommand;
import duck.command.FindKeywordCommand;
import duck.command.ListCommand;
import duck.command.ShowScheduleCommand;

import duck.exception.DuckException;
import duck.exception.DuckExceptionType;
import duck.task.Deadline;
import duck.task.Event;
import duck.task.Task;
import duck.task.Todo;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Deals with making sense of the user command. Static method parse parses the user command and returns a
 * Command object.
 */
public class Parser {

    /**
     * Parses the given user input and returns the corresponding Command object for execution based on the input.
     *
     * @param fullCommand The full user input string for parsing.
     * @param taskList The list of currently saved tasks.
     * @return A Command object based on the parsed user input.
     * @throws DuckException If the parser encounters an invalid command.
     */
    public static Command parse(String fullCommand, TaskList taskList) throws DuckException {
        // Find case based on first word of command
        String[] parsedCommand = fullCommand.split("\\s+", 3);
        assert parsedCommand.length < 4 : "initial split is not done properly.";
        switch (parsedCommand[0]) {

        // "bye" command given
        case "bye":
            return new ByeCommand();

        // "list" command given
        case "list":
            return new ListCommand();

        // "done" command given
        case "done":
            return parseDone(parsedCommand, taskList);

        // "delete" command given
        case "delete":
            return parseDelete(parsedCommand, taskList);

        // "find" command given
        case "find":
            return parseFind(parsedCommand);

        // "schedule" command given
        case "schedule":
            return parseSchedule(parsedCommand);

        // Task command given
        default:
            return parseAdd(fullCommand);
        }
    }

    /**
     * Parses a 'done' instruction from the user and returns a DoneCommand.
     *
     * @param parsedCommand Array of parsed instructions from the parse function.
     * @param taskList Task list where task to set to done is located.
     * @return DoneCommand based on user input.
     * @throws DuckException if invalid command is given.
     */
    public static DoneCommand parseDone(String[] parsedCommand, TaskList taskList) throws DuckException {
        if (parsedCommand.length == 1) {
            throw new DuckException(DuckExceptionType.INVALID_TASK_INDEX);
        }

        String indexString = parsedCommand[1];
        int toSet = Integer.parseInt(indexString);

        if (taskList.isInvalidIndex(toSet)) {
            throw new DuckException(DuckExceptionType.INVALID_TASK_INDEX);
        }

        int doneIndex = toSet - 1;
        return new DoneCommand(doneIndex);
    }

    /**
     * Parses a 'delete' instruction from the user and returns a DeleteCommand.
     *
     * @param parsedCommand Array of parsed instructions from the parse function.
     * @param taskList Task list where task to delete is located.
     * @return DeleteCommand based on user input.
     * @throws DuckException if invalid command is given.
     */
    public static DeleteCommand parseDelete(String[] parsedCommand, TaskList taskList) throws DuckException {
        if (parsedCommand.length == 1) {
            throw new DuckException(DuckExceptionType.INVALID_TASK_INDEX);
        }

        String indexString = parsedCommand[1];
        int toDelete = Integer.parseInt(indexString);

        if (taskList.isInvalidIndex(toDelete)) {
            throw new DuckException(DuckExceptionType.INVALID_TASK_INDEX);
        }

        int deleteIndex = toDelete - 1;
        return new DeleteCommand(deleteIndex);
    }

    /**
     * Parses a 'find' instruction from the user and returns a FindKeywordCommand.
     *
     * @param parsedCommand Array of parsed instructions from the parse function.
     * @return FindKeywordCommand based on user input.
     * @throws DuckException if invalid command is given.
     */
    public static Command parseFind(String[] parsedCommand) throws DuckException {
        if (parsedCommand.length == 1) {
            throw new DuckException(DuckExceptionType.INVALID_FIND);
        }

        String keyword = parsedCommand[1];
        return new FindKeywordCommand(keyword);
    }

    /**
     * Further parses a 'schedule' instruction from the user and returns a ShowScheduleCommand.
     *
     * @param parsedCommand Array of parsed instructions from the parse function.
     * @return ShowScheduleCommand based on user input.
     * @throws DuckException if invalid command is given.
     */
    public static Command parseSchedule(String[] parsedCommand) throws DuckException {
        if (parsedCommand.length == 1) {
            throw new DuckException(DuckExceptionType.INVALID_SCHEDULE);
        }

        String desiredDateString = parsedCommand[1];
        LocalDate desiredDate = LocalDate.parse(desiredDateString);
        return new ShowScheduleCommand(desiredDate);
    }

    /**
     * Further parses an add instruction from the user and returns an AddCommand.
     *
     * @param fullCommand User input string in its entirety.
     * @return AddCommand based on user input.
     * @throws DuckException if invalid command is given.
     */
    public static Command parseAdd(String fullCommand) throws DuckException {
        String[] parsedCommand = fullCommand.split("\\s+", 2);
        String taskName = parsedCommand[0];

        // Incomplete or invalid duck.command
        if (parsedCommand.length == 1) {
            switch (taskName) {
            case "deadline":
                throw new DuckException(DuckExceptionType.MISSING_DEADLINE_DESC);

            case "event":
                throw new DuckException(DuckExceptionType.MISSING_EVENT_DESC);

            case "todo":
                throw new DuckException(DuckExceptionType.MISSING_TODO_DESC);

            default:
                throw new DuckException(DuckExceptionType.INVALID_INPUT);
            }
        }

        Task newTask;
        String taskDetails = parsedCommand[1];

        switch (taskName) {

        // "deadline" command given
        case "deadline": {
            newTask = parseDeadline(taskDetails);
            break;
        }

        // "event" command given
        case "event": {
            newTask = parseEvent(taskDetails);
            break;
        }

        // "todo" command given
        case "todo":
            newTask = new Todo(taskDetails);
            break;

        // Invalid command
        default:
            throw new DuckException(DuckExceptionType.INVALID_INPUT);
        }

        return new AddCommand(newTask);
    }

    /**
     * Further parses a 'parseAdd' instruction from the user and returns a Deadline.
     *
     * @param taskDetails String containing concatenated task details.
     * @return Deadline based on user input.
     * @throws DuckException if invalid taskDetails string is given.
     */
    public static Deadline parseDeadline(String taskDetails) throws DuckException {
        String[] details = taskDetails.split(" /by ");
        String deadlineDescription = details[0];

        if (details.length == 1) {
            throw new DuckException(DuckExceptionType.MISSING_DEADLINE_DATETIME);
        }

        String[] deadline = details[1].split(" ");
        String deadlineDate = deadline[0];
        if (deadline.length == 1) { // only date provided
            return new Deadline(deadlineDescription, LocalDate.parse(deadlineDate));
        } else if (deadline.length == 2) { // date and time provided
            String deadlineTime = deadline[1];
            return new Deadline(deadlineDescription, LocalDate.parse(deadlineDate),
                    LocalTime.parse(deadlineTime));
        } else {
            throw new DuckException(DuckExceptionType.INVALID_DATETIME);
        }
    }

    /**
     * Further parses a 'parseAdd' instruction from the user and returns an Event.
     *
     * @param taskDetails String containing concatenated task details.
     * @return Event based on user input.
     * @throws DuckException if invalid taskDetails string is given.
     */
    public static Event parseEvent(String taskDetails) throws DuckException {
        String[] details = taskDetails.split(" /at ");
        String eventDescription = details[0];

        if (details.length == 1) {
            throw new DuckException(DuckExceptionType.MISSING_EVENT_PERIOD);
        }

        String[] periodRange = details[1].split(" ");

        if (periodRange.length == 2) { // start and end date provided
            String startDate = periodRange[0];
            String endDate = periodRange[1];
            return new Event(eventDescription, LocalDate.parse(startDate),
                    LocalDate.parse(endDate));

        } else if (periodRange.length == 3) { // date, start and end time provided
            String eventDate = periodRange[0];
            String startTime = periodRange[1];
            String endTime = periodRange[2];
            return new Event(eventDescription, LocalDate.parse(eventDate),
                    LocalTime.parse(startTime), LocalTime.parse(endTime));

        } else if (periodRange.length == 4) { // start date and time, end date and time provided
            String startDate = periodRange[0];
            String startTime = periodRange[1];
            String endDate = periodRange[2];
            String endTime = periodRange[3];
            return new Event(eventDescription,
                    LocalDate.parse(startDate), LocalTime.parse(startTime),
                    LocalDate.parse(endDate), LocalTime.parse(endTime));

        } else {
            throw new DuckException(DuckExceptionType.INVALID_PERIOD);
        }
    }
}
