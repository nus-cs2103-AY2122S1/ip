package eightbit.util;

import eightbit.EightBitException;
import eightbit.command.*;
import eightbit.task.Deadline;
import eightbit.task.Event;
import eightbit.task.ToDo;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class Parser {

    public static Command parse(String command) throws EightBitException {
        String[] inputs = command.split(" ");
        CommandType commandType = getCommandType(inputs[0]);

        switch (commandType) {
        case LIST:
            return parseListCommand(command);
        case DONE:
            return parseDoneCommand(command);
        case TODO:
            return parseToDoCommand(command);
        case DEADLINE:
            return parseDeadlineCommand(command);
        case EVENT:
            return parseEventCommand(command);
        case DELETE:
            return parseDeleteCommand(command);
        case BYE:
            return parseByeCommand(command);
        default:
            throw new EightBitException("OOPS!!! I'm sorry, but I don't know what that means :(");
        }
    }

    private static CommandType getCommandType(String command) {
        String uppercase = command.trim().toUpperCase();
        try {
            return CommandType.valueOf(uppercase);
        } catch (IllegalArgumentException e) {
            return CommandType.DEFAULT;
        }
    }

    private static ListCommand parseListCommand(String command) throws EightBitException {
        if (command.split(" ").length > 1) { // extra words after "list"
            throw new EightBitException("OOPS!!! Please remove words after \"list\"");
        }

        return new ListCommand();
    }

    private static DoneCommand parseDoneCommand(String command) throws EightBitException {
        if (command.split(" ").length != 2) { // not in the format "done <integer>"
            throw new EightBitException("OOPS!!! Please enter in this format:\ndone <integer>");
        }

        try {
            Integer.parseInt(command.split(" ")[1]);
        } catch (NumberFormatException e) { // not integer
            throw new EightBitException("OOPS!!! Please enter in this format:\ndone <integer>");
        }

        int index = Integer.parseInt(command.split(" ")[1]) - 1;
        if (index < 0) { // input number < 1
            throw new EightBitException("OOPS!!! Task " + (index + 1) + " does not exist.");
        }

        return new DoneCommand(index);
    }

    private static ToDoCommand parseToDoCommand(String command) throws EightBitException {
        if (command.split(" ").length < 2) { // missing description
            throw new EightBitException("OOPS!!! The description of a todo cannot be empty.");
        }

        String toDoDescription = command.substring(5);
        ToDo toDo = new ToDo(toDoDescription);

        return new ToDoCommand(toDo);
    }

    private static DeadlineCommand parseDeadlineCommand(String command) throws EightBitException {
        if (command.split(" ").length == 1 // missing description and deadline
                || command.substring(9).trim().split(" /by ").length < 2) { // missing either description or date/time
            throw new EightBitException("OOPS!!! Please enter your deadline in this format:\n"
                    + "deadline <description> /by yyyy-mm-dd hh:mm\n"
                    + "Ensure a valid date and time is entered.");
        }

        String[] descriptionAndBy = command.substring(9).split(" /by ");
        String deadlineDescription = descriptionAndBy[0];

        try {
            String dateTime = descriptionAndBy[1];
            LocalDateTime localDateTime = LocalDateTime.parse(dateTime.replace(' ', 'T'));
            Deadline deadline = new Deadline(deadlineDescription, localDateTime);
            return new DeadlineCommand(deadline);
        } catch (DateTimeParseException e) {
            throw new EightBitException("OOPS!!! Please enter your deadline in this format:\n"
                    + "deadline <description> /by yyyy-mm-dd hh:mm\n"
                    + "Ensure a valid date and time is entered.");
        }
    }

    private static EventCommand parseEventCommand(String command) throws EightBitException {
        if (command.split(" ").length == 1 // missing description and date
                || command.substring(6).trim().split(" /at ").length < 2) { // missing either description or date/time
            throw new EightBitException("OOPS!!! Please enter your event in this format:\n"
                    + "event <description> /at yyyy-mm-dd hh:mm\n"
                    + "Ensure a valid date and time is entered.");
        }

        String[] descriptionAndAt = command.substring(6).split(" /at ");
        String eventDescription = descriptionAndAt[0];

        try {
            String dateTime = descriptionAndAt[1];
            LocalDateTime localDateTime = LocalDateTime.parse(dateTime.replace(' ', 'T'));
            Event event = new Event(eventDescription, localDateTime);
            return new EventCommand(event);
        } catch (DateTimeParseException e) {
            throw new EightBitException("OOPS!!! Please enter your event in this format:\n"
                    + "event <description> /at yyyy-mm-dd hh:mm\n"
                    + "Ensure a valid date and time is entered.");
        }
    }

    private static DeleteCommand parseDeleteCommand(String command) throws EightBitException {
        if (command.split(" ").length != 2) { // not in the format "delete <integer>"
            throw new EightBitException("OOPS!!! Please enter in this format:\ndelete <integer>");
        }
        try {
            Integer.parseInt(command.split(" ")[1]);
        } catch (NumberFormatException e) { // not integer
            throw new EightBitException("OOPS!!! Please enter in this format:\ndelete <integer>");
        }

        int index = Integer.parseInt(command.split(" ")[1]) - 1;
        if (index < 0) { // input number < 1
            throw new EightBitException("OOPS!!! Task " + (index + 1) + " does not exist.");
        }

        return new DeleteCommand(index);
    }

    private static ByeCommand parseByeCommand(String command) {
        return new ByeCommand();
    }
}
