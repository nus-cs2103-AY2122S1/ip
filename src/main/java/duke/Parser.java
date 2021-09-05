package duke;

import command.AddCommand;
import command.ByeCommand;
import command.Command;
import command.DoneCommand;
import command.DeleteCommand;
import command.FindDateCommand;
import command.FindKeywordCommand;
import command.ListCommand;

import exception.DukeException;
import exception.DukeExceptionType;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Deals with making sense of the user command. Static method parse parses the user command and returns a Command
 * object.
 */
public class Parser {

    /**
     * Parses the given user input and returns the corresponding Command object for execution based on the input.
     *
     * @param fullCommand The full user input string for parsing.
     * @param taskList The list of currently saved tasks.
     * @return A Command object based on the parsed user input.
     * @throws DukeException If the parser encounters an invalid command.
     */
    public static Command parse(String fullCommand, TaskList taskList) throws DukeException {
        // Find case based on first word of command
        String[] parsedCommand = fullCommand.split("\\s+", 3);
        String commandName = parsedCommand[0];
        switch (commandName) {

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

        // Task command given
        default:
            return parseAdd(fullCommand);
        }
    }

    /**
     * Further parses a 'done' instruction from the user and returns a DoneCommand.
     *
     * @param parsedCommand Array of parsed instructions from the parse function.
     * @param taskList Task list where task to set to done is located.
     * @return DoneCommand based on user input.
     * @throws DukeException if invalid command is given.
     */
    public static DoneCommand parseDone(String[] parsedCommand, TaskList taskList) throws DukeException {
        if (parsedCommand.length == 1) {
            throw new DukeException(DukeExceptionType.INVALID_TASK_INDEX);
        }

        String indexString = parsedCommand[1];
        int toSet = Integer.parseInt(indexString);

        if (taskList.isInvalidIndex(toSet)) {
            throw new DukeException(DukeExceptionType.INVALID_TASK_INDEX);
        }

        int doneIndex = toSet - 1;
        return new DoneCommand(doneIndex);
    }

    /**
     * Further parses a 'delete' instruction from the user and returns a DeleteCommand.
     *
     * @param parsedCommand Array of parsed instructions from the parse function.
     * @param taskList Task list where task to delete is located.
     * @return DeleteCommand based on user input.
     * @throws DukeException if invalid command is given.
     */
    public static DeleteCommand parseDelete(String[] parsedCommand, TaskList taskList) throws DukeException {
        if (parsedCommand.length == 1) {
            throw new DukeException(DukeExceptionType.INVALID_TASK_INDEX);
        }

        String indexString = parsedCommand[1];
        int toDelete = Integer.parseInt(indexString);

        if (taskList.isInvalidIndex(toDelete)) {
            throw new DukeException(DukeExceptionType.INVALID_TASK_INDEX);
        }

        int deleteIndex = toDelete - 1;
        return new DeleteCommand(deleteIndex);
    }

    /**
     * Further parses a 'find' instruction from the user and returns a FindCommand.
     *
     * @param parsedCommand Array of parsed instructions from the parse function.
     * @return FindKeywordCommand or FindDateCommand based on user input.
     * @throws DukeException if invalid command is given.
     */
    public static Command parseFind(String[] parsedCommand) throws DukeException {
        if (parsedCommand.length == 1) {
            throw new DukeException(DukeExceptionType.INVALID_FIND);
        }

        String findDetails = parsedCommand[1];

        if (findDetails.contains("/date")) {
            if (parsedCommand.length == 2) {
                throw new DukeException(DukeExceptionType.MISSING_FIND_DATE);
            }

            LocalDate desiredDate = LocalDate.parse(parsedCommand[2]);
            return new FindDateCommand(desiredDate);

        } else if (findDetails.contains("/keyword")) {
            if (parsedCommand.length == 2) {
                throw new DukeException(DukeExceptionType.MISSING_FIND_KEYWORD);
            }

            String keyword = parsedCommand[2];
            return new FindKeywordCommand(keyword);

        } else {
            throw new DukeException(DukeExceptionType.INVALID_FIND);
        }
    }

    /**
     * Further parses an add instruction from the user and returns an AddCommand.
     *
     * @param fullCommand User input string in its entirety.
     * @return AddCommand based on user input.
     * @throws DukeException if invalid command is given.
     */
    public static Command parseAdd(String fullCommand) throws DukeException {
        String[] parsedCommand = fullCommand.split("\\s+", 2);
        String taskName = parsedCommand[0];

        // Incomplete or invalid command
        if (parsedCommand.length == 1) {
            switch (taskName) {
            case "deadline":
                throw new DukeException(DukeExceptionType.MISSING_DEADLINE_DESC);

            case "event":
                throw new DukeException(DukeExceptionType.MISSING_EVENT_DESC);

            case "todo":
                throw new DukeException(DukeExceptionType.MISSING_TODO_DESC);

            default:
                throw new DukeException(DukeExceptionType.INVALID_INPUT);
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
            throw new DukeException(DukeExceptionType.INVALID_INPUT);
        }

        return new AddCommand(newTask);
    }

    /**
     * Further parses a 'parseAdd' instruction from the user and returns a Deadline.
     *
     * @param taskDetails String containing concatenated task details.
     * @return Deadline based on user input.
     * @throws DukeException if invalid taskDetails string is given.
     */
    public static Deadline parseDeadline(String taskDetails) throws DukeException {
        String[] details = taskDetails.split(" /by ");
        String deadlineDescription = details[0];

        if (details.length == 1) {
            throw new DukeException(DukeExceptionType.MISSING_DEADLINE_DATETIME);
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
            throw new DukeException(DukeExceptionType.INVALID_DATETIME);
        }
    }

    /**
     * Further parses a 'parseAdd' instruction from the user and returns an Event.
     *
     * @param taskDetails String containing concatenated task details.
     * @return Event based on user input.
     * @throws DukeException if invalid taskDetails string is given.
     */
    public static Event parseEvent(String taskDetails) throws DukeException {
        String[] details = taskDetails.split(" /at ");
        String eventDescription = details[0];

        if (details.length == 1) {
            throw new DukeException(DukeExceptionType.MISSING_EVENT_PERIOD);
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
            throw new DukeException(DukeExceptionType.INVALID_PERIOD);
        }
    }
}
