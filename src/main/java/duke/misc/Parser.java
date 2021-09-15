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
        String inputPrefix = input;
        String inputSuffix = "";
        int idx = input.indexOf(' ');
        if (idx >= 0) {
            inputPrefix = input.substring(0, idx);
            inputSuffix = input.substring(idx + 1);
        }
        switch (inputPrefix) {
        case "bye":
            return makeByeCommand(inputSuffix);
        case "list":
            return makeListCommand(inputSuffix);
        case "done":
            return makeDoneCommand(inputSuffix);
        case "delete":
            return makeDeleteCommand(inputSuffix);
        case "find":
            return makeFindCommand(inputSuffix);
        case "deadline":
            return makeAddDeadlineCommand(inputSuffix);
        case "todo":
            return makeTodoCommand(inputSuffix);
        case "event":
            return makeEventCommand(inputSuffix);
        default:
            throw new InvalidCommandException();
        }
    }

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

    private String[] parseInputSuffix(String inputSuffix) {
        String[] args = inputSuffix.split("/");
        for (int i = 0; i < args.length; i++) {
            args[i] = args[i].trim();
        }
        String[] dateAndTime = parseDateTime(args[1]);
        String[] res = new String[3];
        res[0] = args[0];
        res[1] = dateAndTime[0];
        res[2] = dateAndTime[1];
        return res;
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
        String[] args = parseInputSuffix(inputSuffix);
        if (!inputSuffix.matches("[\\s\\S]+/[\\s\\S]+")) {
            throw new InvalidFormatException();
        }

        assert args.length > 0;

        if (DateTime.isInvalidDate(args[1])) {
            throw new InvalidDateException();
        }
        if (DateTime.isInvalidTime(args[2])) {
            throw new InvalidTimeException();
        }
        return new AddDeadlineCommand(args);
    }

    private Command makeEventCommand(String inputSuffix) throws DukeException {
        String[] args = parseInputSuffix(inputSuffix);
        if (!inputSuffix.matches("[\\s\\S]+/[\\s\\S]+")) {
            throw new InvalidFormatException();
        }

        assert args.length > 0;

        if (DateTime.isInvalidDate(args[1])) {
            throw new InvalidDateException();
        }
        if (DateTime.isInvalidTime(args[2])) {
            throw new InvalidTimeException();
        }
        return new AddEventCommand(args);
    }

    private Command makeTodoCommand(String inputSuffix) throws DukeException {
        if (!inputSuffix.matches("[\\s\\S]+")) {
            throw new InvalidCommandException();
        }
        return new AddTodoCommand(inputSuffix);
    }
}
