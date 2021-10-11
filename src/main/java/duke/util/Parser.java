package duke.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.DukeException;
import duke.commands.ByeCommand;
import duke.commands.Command;
import duke.tasks.TaskType;
import duke.commands.DeadlineCommand;
import duke.commands.DeleteCommand;
import duke.commands.DoAfterCommand;
import duke.commands.DoneCommand;
import duke.commands.EventCommand;
import duke.commands.FindCommand;
import duke.commands.ListAllCommand;
import duke.commands.TodoCommand;

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

        userInputNotEmpty(fullCommand);

        switch(firstToken) {
        case "bye":
            return new ByeCommand();
        case "list":
            return new ListAllCommand();
        case "delete":
            return parseDelete(fullCommand);
        case "done":
            return parseDone(fullCommand);
        case "todo":
            return parseTodo(fullCommand);
        case "deadline":
            return parseDeadline(fullCommand);
        case "event":
            return parseEvent(fullCommand);
        case "doafter":
            return parseDoAfter(fullCommand);
        case "find":
            return parseFind(fullCommand);
        default:
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    private static String parseDescription(String inputCommand, TaskType taskType) throws DukeException {
        switch (taskType) {
        case TODO:
            return inputCommand.substring(TodoCommand.TODO_DESC_START);
        case EVENT:
            return inputCommand.split("/at")[0].strip().substring(EventCommand.EVENT_DESC_START);
        case DEADLINE:
            return inputCommand.split("/by")[0].strip().substring(DeadlineCommand.DEADLINE_DESC_START);
        case DOAFTER:
            return inputCommand.split(" /after ")[0].strip().substring(DoAfterCommand.DOAFTER_DESC_START);
        default:
            throw new DukeException("An error occurred in parsing the command! :(");
        }
    }

    private static LocalDateTime parseDateTime(String inputCommand, TaskType taskType) throws DukeException {
        String rawDateTime;
        switch(taskType) {
        case EVENT:
            rawDateTime = inputCommand.split("/at")[1].strip();
            break;
        case DEADLINE:
            rawDateTime = inputCommand.split("/by")[1].strip();
            break;
        case DOAFTER:
            rawDateTime = inputCommand.split(" /after ")[1].strip();
            break;
        default:
            throw new DukeException("An error occurred in parsing the command! :(");
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return LocalDateTime.parse(rawDateTime, formatter);
    }

    private static String parseSearchTerms(String inputCommand) {
        return inputCommand.substring(FindCommand.SEARCH_TERM_START_IDX).strip();
    }

    private static Command parseDone(String fullCommand) throws DukeException {
        try {
            if (fullCommand.split(" ").length <= 1) {
                throw new DukeException("☹ OOPS!!! Please provide a valid task number.");
            }

            // Determine index of task to mark as done
            int index = Integer.parseInt(fullCommand.split(" ")[1]);
            return new DoneCommand(index);
        } catch (NumberFormatException e) {
            throw new DukeException("☹ OOPS!!! Please provide a valid task number.");
        }
    }

    private static Command parseDelete(String fullCommand) throws DukeException {
        if (fullCommand.split(" ").length <= 1) {
            throw new DukeException("☹ OOPS!!! Please provide a valid task number.");
        }
        try {
            // Determine index of task to delete
            int index = Integer.parseInt(fullCommand.split(" ")[1]);
            return new DeleteCommand(index);
        } catch (NumberFormatException e) {
            throw new DukeException("☹ OOPS!!! Please provide a valid task number.");
        }
    }

    private static Command parseTodo(String fullCommand) throws DukeException {
        if (fullCommand.split(" ").length <= 1) {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
        String todoDescription = Parser.parseDescription(fullCommand, TaskType.TODO);

        assert !todoDescription.equals("") : "ToDo description should not be empty!";

        return new TodoCommand(todoDescription);
    }

    private static Command parseDeadline(String fullCommand) throws DukeException {
        // Check for valid description provided
        if (fullCommand.split(" /by ")[0].split(" ").length <= 1) {
            throw new DukeException("☹ OOPS!!! Please provide a valid deadline description.");
        }
        // Check for valid due date provided
        if (fullCommand.split(" /by ").length != 2) {
            throw new DukeException("☹ OOPS!!! Please provide a valid due date.");
        }

        try {
            assert !fullCommand.split(" /by ")[1].strip().equals("")
                    : "Datetime provided for Deadline cannot be empty";

            LocalDateTime dueDateTime = Parser.parseDateTime(fullCommand, TaskType.DEADLINE);
            String deadlineDescription = Parser.parseDescription(fullCommand, TaskType.DEADLINE);

            assert !deadlineDescription.equals("") : "Description provided for Deadline cannot be empty!";

            return new DeadlineCommand(deadlineDescription, dueDateTime);
        } catch (DateTimeParseException e) {
            throw new DukeException("☹ OOPS!!! Please provide a valid due date.");
        }

    }

    private static Command parseEvent(String fullCommand) throws DukeException {
        // Check for valid description provided
        if (fullCommand.split(" /at ")[0].split(" ").length <= 1) {
            throw new DukeException("☹ OOPS!!! Please provide a valid event description.");
        }
        // Check for valid event time provided
        if (fullCommand.split(" /at ").length != 2) {
            throw new DukeException("☹ OOPS!!! Please provide a valid event time.");
        }

        try {
            assert !fullCommand.split(" /at ")[1].strip().equals("")
                    : "Datetime input for Event should not be empty!";

            LocalDateTime eventDateTime = Parser.parseDateTime(fullCommand, TaskType.EVENT);
            String eventDescription = Parser.parseDescription(fullCommand, TaskType.EVENT);

            assert !eventDescription.equals("") : "Description for Event should not be empty!";

            return new EventCommand(eventDescription, eventDateTime);
        } catch (DateTimeParseException e) {
            throw new DukeException("☹ OOPS!!! Please provide a valid event time.");
        }
    }

    private static Command parseDoAfter(String fullCommand) throws DukeException {
        // Check for valid description provided
        if (fullCommand.split(" /after ")[0].split(" ").length <= 1) {
            throw new DukeException("☹ OOPS!!! Please provide a valid do after description.");
        }
        // Check for valid doAfter date provided
        if (fullCommand.split(" /after ").length != 2) {
            throw new DukeException("☹ OOPS!!! Please provide a valid do after date.");
        }

        try {
            LocalDateTime doAfterDateTime = Parser.parseDateTime(fullCommand, TaskType.DOAFTER);
            String doAfterDescription = Parser.parseDescription(fullCommand, TaskType.DOAFTER);
            return new DoAfterCommand(doAfterDescription, doAfterDateTime);
        } catch (DateTimeParseException e) {
            throw new DukeException("☹ OOPS!!! Please provide a valid date.");
        }
    }

    private static Command parseFind(String fullCommand) throws DukeException {
        // Validate command and arguments
        if (fullCommand.split(" ").length <= 1) {
            throw new DukeException("Please provide a valid search term!");
        }

        // Parse search terms
        String searchTerms = Parser.parseSearchTerms(fullCommand);

        assert !searchTerms.equals("") : "Search terms for Find should not be empty!";

        return new FindCommand(searchTerms);
    }

    private static void userInputNotEmpty(String fullCommand) throws DukeException {
        // Check if user input is empty
        if (fullCommand.equals("")) {
            throw new DukeException("Please enter a command!");
        }
        assert fullCommand.split(" ").length >= 1 : "Input should not be empty!";
    }
 }
