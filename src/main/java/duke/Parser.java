package duke;

import duke.command.*;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Parser object that parses all the input from user to commands understood by Duke
 */
public class Parser {
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu HHmm");
    private static String[] parsed;

    private static int parseTaskNo(String parse) {
        int taskNo = Integer.parseInt(parse) - 1;
        return taskNo;
    }

    private static String parseTodoTask() throws IllegalArgumentException {
        if (parsed.length < 2 || parsed[1].equals("")) {
            throw new IllegalArgumentException();
        }
        return parsed[1];
    }

    private static String[] parseEventTask(String command) throws
            IllegalArgumentException,
            ArrayIndexOutOfBoundsException,
            StringIndexOutOfBoundsException {
        String[] details = new String[2];
        String desc = command.split(" /at ")[0].substring(6);
        if (desc.equals("")) {
            throw new IllegalArgumentException();
        }
        details[0] = desc;
        details[1] = command.split(" /at ")[1];
        if (details[1].equals("")) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return details;
    }

    private static String[] parseDeadlineTask(String command) throws
            IllegalArgumentException,
            ArrayIndexOutOfBoundsException,
            StringIndexOutOfBoundsException {
        String[] details = new String[2];
        String desc = command.split(" /by ")[0].substring(9);
        if (desc.equals("")) {
            throw new IllegalArgumentException();
        }
        details[0] = desc;
        details[1] = command.split(" /by ")[1];
        if (details[1].equals("")) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return details;
    }

    private static LocalDateTime parseDate(String dateTime) throws DateTimeException {
        return LocalDateTime.parse(dateTime, formatter);
    }

    /**
     * Parses input line by line
     * @param input the input of the user
     * @return returns a command type to be executed in duke.run()
     */
    public static Command parseCommands(String input) {
        parsed = input.split(" ");
        try {
            switch (parsed[0]) {
                case ByeCommand.COMMAND:
                    return new ByeCommand();
                case ListCommand.COMMAND:
                    return new ListCommand();
                case DoneCommand.COMMAND:
                    return new DoneCommand(parseTaskNo(parsed[1]));
                case DeleteCommand.COMMAND:
                    return new DeleteCommand(parseTaskNo(parsed[1]));
                case TodoCommand.COMMAND:
                    return new TodoCommand(parseTodoTask());
                case EventCommand.COMMAND:
                    String[] eventDetails = parseEventTask(input);
                    return new EventCommand(eventDetails[0], parseDate(eventDetails[1]));
                case DeadlineCommand.COMMAND:
                    String[] deadlineDetails = parseDeadlineTask(input);
                    return new EventCommand(deadlineDetails[0], parseDate(deadlineDetails[1]));
                default:
                    throw new DukeException("Ehhh... (￣ ￣|||) Sorry I do not understand.");
            }
        } catch (DukeException e) {
            return new ErrorCommand(e.getMessage());
        } catch (IllegalArgumentException | StringIndexOutOfBoundsException e) {
            return new ErrorCommand("Don't leave it empty!!" ,"(・`ω´・)");
        } catch (ArrayIndexOutOfBoundsException e) {
            return new ErrorCommand("You forgot the date...", "(-_-)");
        } catch (DateTimeException e) {
            return new ErrorCommand(
                    "Please input a valid date format of 'dd/mm/yyyy HHmm' in 24 Hour Format",
                    "(`ー´)");
        }

    }

}
