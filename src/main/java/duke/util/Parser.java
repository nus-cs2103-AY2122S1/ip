package duke.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.DukeException;
import duke.commands.ByeCommand;
import duke.commands.Command;
import duke.commands.CommandType;
import duke.commands.DeadlineCommand;
import duke.commands.DeleteCommand;
import duke.commands.DoneCommand;
import duke.commands.EventCommand;
import duke.commands.FindCommand;
import duke.commands.ListAllCommand;
import duke.commands.TodoCommand;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.ToDo;

/**
 * Class that represents a Parser object for parsing Strings to Commands
 */
public class Parser {

    /**
     * Returns a Command by parsing a user provided String input
     * that can be executed.
     *
     * @param fullCommand A String input direct from the user.
     * @return A subtype of Command
     * @throws DukeException In the event of parse or invalid argument errors
     */
    public static Command parse(String fullCommand) throws DukeException {
        String firstToken = fullCommand.split(" ")[0];

        switch(firstToken) {
        case "bye":
            return new ByeCommand();
        case "list":
            return new ListAllCommand();
        case "delete":
            try {
                Parser.validateInput(fullCommand, CommandType.DELETE);

                // Determine index of task to delete
                int index = Integer.parseInt(fullCommand.split(" ")[1]);
                return new DeleteCommand(index);

            } catch (NumberFormatException e) {
                throw new DukeException("☹ OOPS!!! Please provide a valid task number.");
            }
        case "done":
            try {
                Parser.validateInput(fullCommand, CommandType.DONE);

                // Determine index of task to mark as done
                int index = Integer.parseInt(fullCommand.split(" ")[1]);
                return new DoneCommand(index);
            } catch (NumberFormatException e) {
                throw new DukeException("☹ OOPS!!! Please provide a valid task number.");
            }
        case "todo":
            Parser.validateInput(fullCommand, CommandType.TODO);
            String todoDescription = Parser.parseDescription(fullCommand, CommandType.TODO);
            return new TodoCommand(todoDescription);
        case "deadline":
            try {
                Parser.validateInput(fullCommand, CommandType.DEADLINE);
                LocalDateTime dueDateTime = Parser.parseDateTime(fullCommand, CommandType.DEADLINE);
                String deadlineDescription = Parser.parseDescription(fullCommand, CommandType.DEADLINE);
                return new DeadlineCommand(deadlineDescription, dueDateTime);
            } catch (DateTimeParseException e) {
                throw new DukeException("☹ OOPS!!! Please provide a valid due date.");
            }
        case "event":
            try {
                Parser.validateInput(fullCommand, CommandType.EVENT);
                LocalDateTime eventDateTime = Parser.parseDateTime(fullCommand, CommandType.EVENT);
                String eventDescription = Parser.parseDescription(fullCommand, CommandType.EVENT);
                return new EventCommand(eventDescription, eventDateTime);
            } catch (DateTimeParseException e) {
                throw new DukeException("☹ OOPS!!! Please provide a valid event time.");
            }
        case "find":
            Parser.validateInput(fullCommand, CommandType.FIND);

            // Parse search terms
            String searchTerms = Parser.parseSearchTerms(fullCommand);
            return new FindCommand(searchTerms);
        default:
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    private static String parseDescription(String inputCommand, CommandType commandType) throws DukeException {
        switch (commandType) {
        case TODO:
            return inputCommand.substring(ToDo.TODO_DESC_START);
        case EVENT:
            return inputCommand.split("/at")[0].strip().substring(Event.EVENT_DESC_START);
        case DEADLINE:
            return inputCommand.split("/by")[0].strip().substring(Deadline.DEADLINE_DESC_START);
        default:
            throw new DukeException("An error occurred in parsing the command! :(");
        }
    }

    private static LocalDateTime parseDateTime(String inputCommand, CommandType commandType) throws DukeException {
        String rawDateTime;
        switch(commandType) {
        case EVENT:
            rawDateTime = inputCommand.split("/at")[1].strip();
            break;
        case DEADLINE:
            rawDateTime = inputCommand.split("/by")[1].strip();
            break;
        default:
            throw new DukeException("An error occurred in parsing the command! :(");
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return LocalDateTime.parse(rawDateTime, formatter);
    }

    private static void validateInput(String inputCommand, CommandType commandType) throws DukeException {
        switch(commandType) {
        case DONE:
            //fallthrough
        case DELETE:
            if (inputCommand.split(" ").length <= 1) {
                throw new DukeException("☹ OOPS!!! Please provide a valid task number.");
            }
            break;
        case TODO:
            if (inputCommand.split(" ").length <= 1) {
                throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
            }
            break;
        case DEADLINE:
            // Check for valid description provided
            if (inputCommand.split(" /by ")[0].split(" ").length <= 1) {
                throw new DukeException("☹ OOPS!!! Please provide a valid deadline description.");
            }
            // Check for valid due date provided
            if (inputCommand.split(" /by ").length != 2) {
                throw new DukeException("☹ OOPS!!! Please provide a valid due date.");
            }
            break;
        case EVENT:
            // Check for valid description provided
            if (inputCommand.split(" /at ")[0].split(" ").length <= 1) {
                throw new DukeException("☹ OOPS!!! Please provide a valid event description.");
            }
            // Check for valid event time provided
            if (inputCommand.split(" /at ").length != 2) {
                throw new DukeException("☹ OOPS!!! Please provide a valid event time.");
            }
            break;
        case FIND:
            // Validate command and arguments
            if (inputCommand.split(" ").length <= 1) {
                throw new DukeException("Please provide a valid search term!");
            }
            break;
        case BYE:
            //fallthrough
        case LIST:
            //fallthrough
        default:
        }
    }

    private static String parseSearchTerms(String inputCommand) {
        return inputCommand.substring(FindCommand.SEARCH_TERM_START_IDX).strip();
    }
}
