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
        String prefixArguments = input;
        String suffixArguments = "";
        int idx = input.indexOf(' ');
        if (idx >= 0) {
            prefixArguments = input.substring(0, idx);
            suffixArguments = input.substring(idx + 1);
        }
        switch (prefixArguments) {
        case "bye":
            return makeByeCommand(suffixArguments);
        case "list":
            return makeListCommand(suffixArguments);
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

    // Extracts the date and time from user input.
    private String[] parseDateTime(String datetime) {
        int idx = datetime.indexOf(" ");
        String[] res = {"x", "x"};
        if (idx >= 0) {
            res[0] = datetime.substring(0, idx);
            res[1] = datetime.substring(idx + 1);
            return res;
        }
        res[0] = datetime;
        return res;
    }

    // Extracts the description and date/time from user input.
    private String[] parseSuffixArgs(String suffixArguments) throws DukeException {
        if (!suffixArguments.matches("[\\s\\S]+/[\\s\\S]+")) {
            throw new InvalidFormatException();
        }

        String[] args = suffixArguments.split("/");
        for (int i = 0; i < args.length; i++) {
            args[i] = args[i].trim();
        }

        String[] dateAndTime = parseDateTime(args[1]);

        if (DateTime.isInvalidDate(dateAndTime[0])) {
            throw new InvalidDateException();
        }
        if (isInvalidTime(dateAndTime[1])) {
            throw new InvalidTimeException();
        }

        String[] res = new String[3];
        res[0] = args[0];
        res[1] = dateAndTime[0];
        res[2] = dateAndTime[1];

        return res;
    }

    private boolean isInvalidTime(String time) {
        if (!DateTime.isInvalidTime(time)) {
            return false;
        }
        int toIdx = time.indexOf("t");
        String timeStart = time.substring(0, toIdx);
        String timeEnd = time.substring(toIdx + 1);
        return DateTime.isInvalidTime(timeStart) || DateTime.isInvalidTime(timeEnd);
    }

    private Command makeByeCommand(String inputSuffix) throws DukeException {
        if (!inputSuffix.isEmpty()) {
            throw new InvalidCommandException();
        }
        return new ByeCommand();
    }

    private Command makeListCommand(String inputSuffix) throws DukeException {
        if (!inputSuffix.isEmpty()) {
            throw new InvalidCommandException();
        }
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
