package duke;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

    public static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<command>\\S+)(\\s*)(?<args>.*)");

    public static Command parse (String userInput) throws DukeException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput);
        if (!matcher.matches()) {
            throw new DukeException("You better type sumthin or else..");
        }

        final String command = matcher.group("command");
        final String args = matcher.group("args");

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

            case HelpCommand.COMMAND_WORD: // Fallthrough
            default:
                return new HelpCommand();
        }
    }

    public static Command prepareDone(String args) throws DukeException {
        // Make sure there is argument to determine task to mark as done
        if (args.equals("")) {
            throw new DukeException("Done what brah??\n\t" + DoneCommand.USAGE_TEXT);
        }
        // Makes sure that key phrase is a valid int
        try {
            int index = Integer.parseInt(args);
            return new DoneCommand(index);
        }
        catch (NumberFormatException e) {
            throw new DukeException("Not a number!\n\t" + DoneCommand.USAGE_TEXT);
        }
    }

    public static Command prepareDelete(String args) throws DukeException {
        // Make sure there is argument to determine task to mark as done
        if (args.equals("")) {
            throw new DukeException("Done what brah??\n\t" + DeleteCommand.USAGE_TEXT);
        }
        // Makes sure that key phrase is a valid int
        try {
            int index = Integer.parseInt(args);
            return new DeleteCommand(index);
        }
        catch (NumberFormatException e) {
            throw new DukeException("Not a number!\n\t" + DeleteCommand.USAGE_TEXT);
        }
    }

    public static Command prepareTodo(String args) throws DukeException {
        // Make sure there is argument to record as task
        if (args.equals("")) {
            throw new DukeException("Todo what brah??\n\t" + TodoCommand.USAGE_TEXT);
        }
        return new TodoCommand(args);
    }

    public static Command prepareDeadline(String args) throws DukeException {
        // Make sure there is argument to record as task
        if (args.equals("")) {
            throw new DukeException("Deadline for what brah??\n\t" + DeleteCommand.USAGE_TEXT);
        }
        // Makes sure argument consists of task and datetime
        String[] desc_date = args.split(" /by ", 2);
        if (desc_date.length != 2) {
            throw new DukeException("Deadline by when brah??\n\t" + DeleteCommand.USAGE_TEXT);
        }
        // Make sure date format is correct
        try {
            String desc = desc_date[0];
            LocalDate date = LocalDate.parse(desc_date[1]);
            return new DeadlineCommand(desc, date);
        }
        catch (DateTimeParseException e) {
            throw new DukeException("Follow the gahdam format!!\n\t" + DeleteCommand.USAGE_TEXT);
        }
    }

    public static Command prepareEvent(String args) throws DukeException {
        // Make sure there is argument to record as task
        if (args.equals("")) {
            throw new DukeException("Event for what brah??\n\t" + EventCommand.USAGE_TEXT);
        }
        // Makes sure argument consists of task and datetime
        String[] desc_date = args.split(" /at ", 2);
        if (desc_date.length != 2) {
            throw new DukeException("Event when brah??\n\t" + EventCommand.USAGE_TEXT);
        }
        // Make sure date format is correct
        try {
            String desc = desc_date[0];
            LocalDate date = LocalDate.parse(desc_date[1]);
            return new EventCommand(desc, date);
        }
        catch (DateTimeParseException e) {
            throw new DukeException("Follow the gahdam format!!\n\t" + EventCommand.USAGE_TEXT);
        }
    }
}
