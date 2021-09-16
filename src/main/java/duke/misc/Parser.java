package duke.misc;

import java.io.IOException;

import duke.command.AddDeadlineCommand;
import duke.command.AddEventCommand;
import duke.command.AddTodoCommand;
import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.exception.DukeException;
import duke.exception.InvalidCommandException;
import duke.exception.InvalidDateException;
import duke.exception.InvalidFormatException;
import duke.exception.InvalidIndexException;
import duke.exception.InvalidTimeException;
import duke.task.DateTime;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * Parser class to handle parsing of user command inputs.
 */
public class Parser {

    /**
     * Constructor for Parser class.
     */
    public Parser() {
    }

    /**
     * Parses the command input by user.
     *
     * @param input User's command input.
     * @return Appropriate executable command according to user command input.
     * @throws DukeException In case the input is invalid.
     * @throws IOException In case the directory of data storage file is non-existent.
     */
    public Command parseCommand(String input) throws DukeException, IOException {
        int idx = input.indexOf(' ');
        if (idx >= 0) {
            String prefixArguments = input.substring(0, idx);
            String suffixArguments = input.substring(idx + 1);
            return handleMultiArgCommand(prefixArguments, suffixArguments);
        } else {
            return handleOneArgCommand(input);
        }
    }

    // Handles one argument commands.
    private Command handleOneArgCommand(String input) throws DukeException {
        switch (input) {
        case "bye":
            return makeByeCommand();
        case "list":
            return makeListCommand();
        default:
            throw new InvalidCommandException();
        }
    }

    // Handles multi argument commands.
    private Command handleMultiArgCommand(
            String prefixArguments, String suffixArguments) throws DukeException {
        switch (prefixArguments) {
        case "done":
            return makeDoneCommand(suffixArguments);
        case "delete":
            return makeDeleteCommand(suffixArguments);
        case "find":
            return makeFindCommand(suffixArguments);
        case "deadline":
            return makeAddDeadlineCommand(suffixArguments);
        case "todo":
            return makeTodoCommand(suffixArguments);
        case "event":
            return makeEventCommand(suffixArguments);
        default:
            throw new InvalidCommandException();
        }
    }

    /**
     * Parses the user input for event time range.
     *
     * @param time User input for event time range.
     * @return Parsed user input for event time range.
     */
    public static String[] parseEventTime(String time) {
        return time.split("t");
    }

    /**
     * Handles parsing of the stored tasks data for Storage class.
     *
     * @param taskData The task's stored data.
     * @return The appropriate Task object.
     */
    public static Task parseStoredTaskData(String taskData) {
        String[] args = taskData.split(" // ");
        String taskType = args[0];
        String description = args[2];
        Boolean isTaskDone = Integer.parseInt(args[1]) != 0;

        assert args.length == 3 || args.length == 5;

        if (taskType.equals("Todo")) {
            return new Todo(description, isTaskDone);
        }

        String date = args[3];
        String time = args[4];
        if (taskType.equals("Event")) {
            return new Event(description, date, time, isTaskDone);
        } else {
            assert taskType.equals("Deadline");
            return new Deadline(description, date, time, isTaskDone);
        }
    }

    // Extracts the date and time from user input.
    private String[] parseDateTime(String dateTimeArg) {
        int idx = dateTimeArg.indexOf(" ");

        // The "x" literals serve as a filler to prevent null array variables.
        String[] res = {"x", "x"};

        if (idx >= 0) {
            // Date argument
            res[0] = dateTimeArg.substring(0, idx);
            // Time argument
            res[1] = dateTimeArg.substring(idx + 1);
            return res;
        }

        res[0] = dateTimeArg;
        return res;
    }

    // Extracts the description and date/time from user input.
    private String[] parseSuffixArgs(String suffixArguments) throws DukeException {
        if (!suffixArguments.matches("[\\s\\S]+/[\\s\\S]+")) {
            throw new InvalidFormatException();
        }

        String[] args = suffixArguments.split("/");
        String description = args[0].trim();
        String dateTimeArg = args[1].trim();

        String[] dateAndTime = parseDateTime(dateTimeArg);
        String date = dateAndTime[0];
        String time = dateAndTime[1];
        if (DateTime.isInvalidDate(date)) {
            throw new InvalidDateException();
        }
        if (isInvalidTime(time)) {
            throw new InvalidTimeException();
        }

        String[] res = new String[3];
        res[0] = description;
        res[1] = date;
        res[2] = time;

        return res;
    }

    // Checks the validity of time for both deadline and event tasks.
    private boolean isInvalidTime(String time) {
        // Checks time for deadline task.
        if (!DateTime.isInvalidTime(time)) {
            return false;
        }

        // Checks time range for event task.
        int tIdx = time.indexOf("t");
        String timeStart = time.substring(0, tIdx);
        String timeEnd = time.substring(tIdx + 1);
        boolean isInvalidTimeStart = DateTime.isInvalidTime(timeStart);
        boolean isInvalidTimeEnd = DateTime.isInvalidTime(timeEnd);

        return isInvalidTimeStart || isInvalidTimeEnd;
    }

    private Command makeByeCommand() {
        return new ByeCommand();
    }

    private Command makeListCommand() {
        return new ListCommand();
    }

    private Command makeDeleteCommand(String inputSuffix) throws DukeException {
        try {
            int taskIdx = Integer.parseInt(inputSuffix);
            return new DeleteCommand(taskIdx);
        } catch (NumberFormatException e) {
            throw new InvalidIndexException();
        }
    }

    private Command makeDoneCommand(String inputSuffix) throws DukeException {
        try {
            int taskIdx = Integer.parseInt(inputSuffix);
            return new DoneCommand(taskIdx);
        } catch (NumberFormatException e) {
            throw new InvalidIndexException();
        }
    }

    private Command makeFindCommand(String inputSuffix) throws InvalidCommandException {
        if (!inputSuffix.matches("[\\s\\S]+")) {
            throw new InvalidCommandException();
        }
        return new FindCommand(inputSuffix);
    }

    private Command makeAddDeadlineCommand(String inputSuffix) throws DukeException {
        String[] args = parseSuffixArgs(inputSuffix);
        return new AddDeadlineCommand(args);
    }

    private Command makeEventCommand(String inputSuffix) throws DukeException {
        String[] args = parseSuffixArgs(inputSuffix);
        return new AddEventCommand(args);
    }

    private Command makeTodoCommand(String inputSuffix) throws DukeException {
        if (!inputSuffix.matches("[\\s\\S]+")) {
            throw new InvalidCommandException();
        }
        return new AddTodoCommand(inputSuffix);
    }
}
