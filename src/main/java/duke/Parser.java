package duke;

import duke.command.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents parser to parse user input
 */
public class Parser {

    /** Regex of user input format */
    public static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<command>\\S+)(\\s*)(?<args>.*)");

    /**
     * Returns command invoked by user with parsed arguments to execute.
     * If command word given by user is invalid, HelpCommand is returned.
     *
     * @param userInput User input with command word and arguments to parse.
     * @return Command to execute.
     * @throws DukeException if user input does not match BASIC_COMMAND_FORMAT.
     */
    public static Command parse (String userInput) throws DukeException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput);
        if (!matcher.matches()) {
            throw new DukeException("You better type sumthin or else..");
        }

        String command = matcher.group("command");
        String args = matcher.group("args");

        switch (command) {
        case ByeCommand.COMMAND_WORD:
            return new ByeCommand();
        case ListCommand.COMMAND_WORD:
            return new ListCommand();
        case DoneCommand.COMMAND_WORD:
            return prepareDone(args);
        case DeleteCommand.COMMAND_WORD:
            return prepareDelete(args);
        case TodoCommand.COMMAND_WORD:
            return prepareTodo(args);
        case DeadlineCommand.COMMAND_WORD:
            return prepareDeadline(args);
        case EventCommand.COMMAND_WORD:
            return prepareEvent(args);
        case FindCommand.COMMAND_WORD:
            return new FindCommand(args);
        case HelpCommand.COMMAND_WORD: // Fallthrough
        default:
            return new HelpCommand();
        }
    }

    /**
     * Returns DoneCommand to execute.
     *
     * @param args User input argument to determine index of task to mark as done.
     * @return DoneCommand to execute.
     * @throws DukeException If there are no arguments given or argument is not an integer.
     */
    public static Command prepareDone(String args) throws DukeException {
        // Make sure there is argument to determine task to mark as done
        assert (args != null);
        if (args.equals("")) {
            throw new DukeException("Done what brah??\n\t" + DoneCommand.USAGE_TEXT);
        }

        // Makes sure that key phrase is a valid int
        try {
            int index = Integer.parseInt(args) - 1;
            return new DoneCommand(index);
        } catch (NumberFormatException e) {
            throw new DukeException("Not a number!\n\t" + DoneCommand.USAGE_TEXT);
        }
    }

    /**
     * Returns DeleteCommand to execute.
     *
     * @param args User input argument to determine index of task to mark delete from tasks.
     * @return DeleteCommand to execute.
     * @throws DukeException If there are no arguments given or argument is not an integer.
     */
    public static Command prepareDelete(String args) throws DukeException {
        // Make sure there is argument to determine task to mark as done
        assert (args != null);
        if (args.equals("")) {
            throw new DukeException("Delete what brah??\n\t" + DeleteCommand.USAGE_TEXT);
        }

        // Makes sure that key phrase is a valid int
        try {
            int index = Integer.parseInt(args) - 1;
            return new DeleteCommand(index);
        } catch (NumberFormatException e) {
            throw new DukeException("Not a number!\n\t" + DeleteCommand.USAGE_TEXT);
        }
    }

    /**
     * Returns TodoCommand to execute.
     *
     * @param args User input argument of to do task description.
     * @return TodoCommand to execute.
     * @throws DukeException If there are no arguments given.
     */
    public static Command prepareTodo(String args) throws DukeException {
        // Make sure there is argument to record as task
        assert (args != null);
        if (args.equals("")) {
            throw new DukeException("Todo what brah??\n\t" + TodoCommand.USAGE_TEXT);
        }
        return new TodoCommand(args);
    }

    /**
     * Returns DeadlineCommand to execute.
     *
     * @param args User input argument of deadline task description and date.
     * @return DeadlineCommand to execute.
     * @throws DukeException If there are no arguments given, or given in wrong format.
     */
    public static Command prepareDeadline(String args) throws DukeException {
        // Make sure there is argument to record as task
        assert (args != null);
        if (args.equals("")) {
            throw new DukeException("Deadline for what brah??\n\t" + DeadlineCommand.USAGE_TEXT);
        }

        // Makes sure argument consists of task and datetime
        String[] desc_date = args.split(" /by ", 2);
        if (desc_date.length != 2) {
            throw new DukeException("Deadline by when brah??\n\t" + DeadlineCommand.USAGE_TEXT);
        }

        // Make sure date format is correct
        try {
            String desc = desc_date[0];
            LocalDate date = LocalDate.parse(desc_date[1]);
            return new DeadlineCommand(desc, date);
        } catch (DateTimeParseException e) {
            throw new DukeException("Follow the gahdam format!!\n\t" + DeadlineCommand.USAGE_TEXT);
        }
    }

    /**
     * Returns EventCommand to execute.
     *
     * @param args User input argument of event task description and date.
     * @return EventCommand to execute.
     * @throws DukeException If there are no arguments given, or given in wrong format.
     */
    public static Command prepareEvent(String args) throws DukeException {
        // Make sure there is argument to record as task
        assert (args != null);
        if (args.equals("")) {
            throw new DukeException("Event for what brah??\n\t" + EventCommand.USAGE_TEXT);
        }

        // Makes sure argument consists of task and datetime
        String[] desc_date = args.split(" /at ", 2);
        if (desc_date.length != 2) {
            throw new DukeException("Event when brah??\n\t" + EventCommand.USAGE_TEXT);
        }

        // Make sure date format is correct
        String desc = desc_date[0];
        try {
            LocalDate date = LocalDate.parse(desc_date[1]);
            return new EventCommand(desc, date);
        } catch (DateTimeParseException e) {
            throw new DukeException("Follow the gahdam format!!\n\t" + EventCommand.USAGE_TEXT);
        }
    }
}
