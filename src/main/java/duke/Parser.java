package duke;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ErrorCommand;
import duke.command.EventCommand;
import duke.command.FindCommand;
import duke.command.HelloCommand;
import duke.command.ListCommand;
import duke.command.SnoozeCommand;
import duke.command.TodoCommand;
import duke.command.UndoCommand;

/**
 * Parser object that parses all the input from user to commands understood by Duke.
 */
public class Parser {
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu HHmm");

    private static final int TODO_PREFIX_LENGTH = 5;
    private static final int EVENT_PREFIX_LENGTH = 6;
    private static final int DEADLINE_PREFIX_LENGTH = 9;

    private static final String UNIDENTIFIED_COMMAND_MESSAGE = "Ehhh... (owO) Sorry sir I do not understand.";
    private static final String EMPTY_INPUT_MESSAGE = "Don't leave it empty!!";
    private static final String EMPTY_INPUT_EMOTICON = ">_>";
    private static final String MISSING_DETAILS_MESSAGE = "Be specific!!!";
    private static final String MISSING_DETAILS_EMOTICON = "(-_-)";
    private static final String INCORRECT_DATE_MESSAGE
            = "Please input a valid date format of 'dd/mm/yyyy HHmm' in 24 Hour Format";
    private static final String INCORRECT_DATE_EMOTICON = "(`-`)";


    private static String[] parsed;

    private static int parseTaskNo(String parse) {
        int taskNo = Integer.parseInt(parse) - 1;
        return taskNo;
    }

    private static String parseTodoTask(String command) throws IllegalArgumentException {
        if (parsed.length < 2 || parsed[1].equals("")) {
            throw new IllegalArgumentException();
        }
        String desc = command.substring(TODO_PREFIX_LENGTH);
        return desc;
    }

    private static String[] parseEventTask(String command) throws
            IllegalArgumentException,
            ArrayIndexOutOfBoundsException,
            StringIndexOutOfBoundsException {

        String[] eventDetails = new String[2];
        String desc = command.split(" /at ")[0].substring(EVENT_PREFIX_LENGTH);
        if (desc.equals("")) {
            throw new IllegalArgumentException();
        }
        eventDetails[0] = desc;
        eventDetails[1] = command.split(" /at ")[1];
        if (eventDetails[1].equals("")) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return eventDetails;
    }

    private static String[] parseDeadlineTask(String command) throws
            IllegalArgumentException,
            ArrayIndexOutOfBoundsException,
            StringIndexOutOfBoundsException {

        String[] deadlineDetails = new String[2];
        String desc = command.split(" /by ")[0].substring(DEADLINE_PREFIX_LENGTH);
        if (desc.equals("")) {
            throw new IllegalArgumentException();
        }
        deadlineDetails[0] = desc;
        deadlineDetails[1] = command.split(" /by ")[1];
        if (deadlineDetails[1].equals("")) {
            throw new IllegalArgumentException();
        }
        return deadlineDetails;
    }

    private static String[] parsedSnooze(String command) throws
            IllegalArgumentException,
            ArrayIndexOutOfBoundsException,
            StringIndexOutOfBoundsException {

        String[] snoozeDetails = command.split(" ");
        if (snoozeDetails[1].equals("") || snoozeDetails[2].equals("")) {
            throw new IllegalArgumentException();
        }
        snoozeDetails[2] = snoozeDetails[2] + " " + snoozeDetails[3];
        return snoozeDetails;
    }

    private static LocalDateTime parseDate(String dateTime) throws DateTimeException {
        return LocalDateTime.parse(dateTime, formatter);
    }

    private static String parseKeyword(String input) throws IllegalArgumentException {
        String keyword = input.substring(4).strip().toLowerCase();
        if (keyword.equals("")) {
            throw new IllegalArgumentException();
        }
        return keyword;
    }

    /**
     * Parses input according to the different commands.
     *
     * @param input Input of the user
     * @return  Command type to be executed in duke.run()
     */
    public static Command parseCommands(String input) {
        assert !input.isEmpty() : "input should not be empty";
        parsed = input.split(" ");
        try {
            switch (parsed[0]) {
            case HelloCommand.COMMAND:
                return new HelloCommand();
            case ByeCommand.COMMAND:
                return new ByeCommand();
            case ListCommand.COMMAND:
                return new ListCommand();
            case DoneCommand.COMMAND:
                return new DoneCommand(parseTaskNo(parsed[1]));
            case DeleteCommand.COMMAND:
                return new DeleteCommand(parseTaskNo(parsed[1]));
            case TodoCommand.COMMAND:
                return new TodoCommand(parseTodoTask(input));
            case EventCommand.COMMAND:
                String[] eventDetails = parseEventTask(input);
                return new EventCommand(eventDetails[0], parseDate(eventDetails[1]));
            case DeadlineCommand.COMMAND:
                String[] deadlineDetails = parseDeadlineTask(input);
                return new DeadlineCommand(deadlineDetails[0], parseDate(deadlineDetails[1]));
            case FindCommand.COMMAND:
                return new FindCommand(parseKeyword(input));
            case UndoCommand.COMMAND:
                return new UndoCommand();
            case SnoozeCommand.COMMAND:
                String[] snooze = parsedSnooze(input);
                int taskNo = parseTaskNo(snooze[1]);
                return new SnoozeCommand(taskNo, parseDate(snooze[2]));
            default:
                throw new DukeException(UNIDENTIFIED_COMMAND_MESSAGE);
            }
        } catch (DukeException e) {
            return new ErrorCommand(e.getMessage());
        } catch (IllegalArgumentException | StringIndexOutOfBoundsException e) {
            return new ErrorCommand(EMPTY_INPUT_MESSAGE, EMPTY_INPUT_EMOTICON);
        } catch (ArrayIndexOutOfBoundsException e) {
            return new ErrorCommand(MISSING_DETAILS_MESSAGE, MISSING_DETAILS_EMOTICON);
        } catch (DateTimeException e) {
            return new ErrorCommand(INCORRECT_DATE_MESSAGE, INCORRECT_DATE_EMOTICON);
        }
    }

}
