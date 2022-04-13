package duke.parser;

import java.time.LocalDate;

import duke.commands.AddCommand;
import duke.commands.Command;
import duke.commands.Command.CommandType;
import duke.commands.EditCommand;
import duke.commands.ErrorCommand;
import duke.commands.ExitCommand;
import duke.commands.ListCommand;
import duke.commands.SearchCommand;
import duke.commands.SortCommand;
import duke.errors.EmptyDeadlineDateException;
import duke.errors.EmptyDeadlineDescriptionException;
import duke.errors.EmptyEventDateException;
import duke.errors.EmptyEventDescriptionException;
import duke.errors.EmptyListNumberException;
import duke.errors.EmptySearchStringException;
import duke.errors.EmptyToDoDescriptionException;
import duke.errors.EscapeCharacterException;
import duke.errors.InvalidCommandException;
import duke.errors.InvalidDateFormatException;
import duke.errors.InvalidListNumberException;
import duke.errors.ListFullException;
import duke.errors.PollutedExitCommandException;
import duke.errors.PollutedListCommandException;
import duke.errors.PollutedSortCommandException;
import duke.errors.SeparatorInStringException;
import duke.errors.TooManyInputsException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.TaskList;
import duke.task.ToDo;

/**
 * This class helps to parse the cleaned user input after the input validation process to produce the
 * appropriate Command object for execution.
 */
public class Parser {

    /**
     * This command parses the clean user input to produce the appropriate
     * Command object.
     *
     * @param fullCommand The cleaned user input
     * @param taskList The current TaskList
     * @return The appropriate Command object
     */
    public static Command parse(String fullCommand, TaskList taskList) {
        Cleaner cl = new Cleaner();
        String cleanCommand = cl.clean(fullCommand, taskList.getCapacity());
        assert !cleanCommand.isBlank() : "Command is blank!";

        String firstWord = cleanCommand.split(" ")[0];


        switch (firstWord) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "todo":
            return todoParser(cleanCommand);
        case "deadline":
            return deadlineParser(cleanCommand);
        case "event":
            return eventParser(cleanCommand);
        case "done":
            return markDoneParser(cleanCommand);
        case "undo":
            return markUndoParser(cleanCommand);
        case "delete":
            return deleteParser(cleanCommand);
        case "find":
            return findParser(cleanCommand);
        case "sort":
            return new SortCommand();
        default:
            assert !firstWord.equals("error") : "Invalid fallthrough.";
            return errorParser(cleanCommand);
        }


    }

    /**
     * This method parses the command for the find Command.
     *
     * @param input The cleaned user input
     * @return The appropriate Command object
     */
    public static Command findParser(String input) {
        String searchString = input.substring(5);
        return new SearchCommand(searchString);
    }

    /**
     * This method parses the command for the todo Command.
     *
     * @param input The cleaned user input
     * @return The appropriate Command object
     */
    public static Command todoParser(String input) {
        String description = input.substring(5).strip();
        assert !description.isBlank() : "Description is blank!";
        return new AddCommand(new ToDo(description));
    }

    /**
     * This method parses the command for the event Command.
     *
     * @param input The cleaned user input
     * @return The appropriate Command object
     */
    public static Command eventParser(String input) {
        String withoutEvent = input.substring(6).strip();
        String[] eventArray = withoutEvent.split("/at");
        String description = eventArray[0].strip();
        assert !description.isBlank() : "Description is blank!";
        LocalDate date = CustomDateFormatter.getLocalDateFromString(eventArray[1].strip());
        return new AddCommand(new Event(description, date));
    }

    /**
     * This method parses the command for the deadline Command.
     *
     * @param input The cleaned user input
     * @return The appropriate Command object
     */
    public static Command deadlineParser(String input) {
        String withoutDeadline = input.substring(9).strip();
        String[] deadlineArray = withoutDeadline.split("/by");
        String description = deadlineArray[0].strip();
        assert !description.isBlank() : "Description is blank!";
        LocalDate date = CustomDateFormatter.getLocalDateFromString(deadlineArray[1].strip());
        return new AddCommand(new Deadline(description, date));
    }

    /**
     * This method parses the command for the done Command.
     *
     * @param input The cleaned user input
     * @return The appropriate Command object
     */
    public static Command markDoneParser(String input) {
        int index = Integer.parseInt(input.substring(5));
        return new EditCommand(CommandType.DONE, index);
    }

    /**
     * This method parses the command for the undo Command.
     *
     * @param input The cleaned user input
     * @return The appropriate Command object
     */
    public static Command markUndoParser(String input) {
        int index = Integer.parseInt(input.substring(5));
        return new EditCommand(CommandType.UNDO, index);
    }

    /**
     * This method parses the command for the delete Command.
     *
     * @param input The cleaned user input
     * @return The appropriate Command object
     */
    public static Command deleteParser(String input) {
        int index = Integer.parseInt(input.substring(7));
        return new EditCommand(CommandType.DELETE, index);
    }

    /**
     * This method parses the command for the error Command.
     *
     * @param errorParse The cleaned user input
     * @return The appropriate Command object
     */
    public static Command errorParser(String errorParse) {
        switch (errorParse) {
        case "INVALID_COMMAND_EXCEPTION":
            return new ErrorCommand(new InvalidCommandException());
        case "ESCAPE_CHARACTER_EXCEPTION":
            return new ErrorCommand(new EscapeCharacterException());
        case "POLLUTED_LIST_COMMAND_EXCEPTION":
            return new ErrorCommand(new PollutedListCommandException());
        case "EMPTY_TODO_DESCRIPTION_EXCEPTION":
            return new ErrorCommand(new EmptyToDoDescriptionException());
        case "EMPTY_DEADLINE_DESCRIPTION_EXCEPTION":
            return new ErrorCommand(new EmptyDeadlineDescriptionException());
        case "EMPTY_DEADLINE_DATE_EXCEPTION":
            return new ErrorCommand(new EmptyDeadlineDateException());
        case "EMPTY_EVENT_DESCRIPTION_EXCEPTION":
            return new ErrorCommand(new EmptyEventDescriptionException());
        case "EMPTY_EVENT_DATE_EXCEPTION":
            return new ErrorCommand(new EmptyEventDateException());
        case "EMPTY_LIST_NUMBER_EXCEPTION":
            return new ErrorCommand(new EmptyListNumberException());
        case "TOO_MANY_INPUTS_EXCEPTION":
            return new ErrorCommand(new TooManyInputsException());
        case "INVALID_LIST_NUMBER_EXCEPTION":
            return new ErrorCommand(new InvalidListNumberException());
        case "LIST_FULL_EXCEPTION":
            return new ErrorCommand(new ListFullException());
        case "SEPARATOR_IN_STRING_EXCEPTION":
            return new ErrorCommand(new SeparatorInStringException());
        case "INVALID_DATE_FORMAT_EXCEPTION":
            return new ErrorCommand(new InvalidDateFormatException());
        case "POLLUTED_EXIT_COMMAND_EXCEPTION":
            return new ErrorCommand(new PollutedExitCommandException());
        case "EMPTY_SEARCH_STRING_EXCEPTION":
            return new ErrorCommand(new EmptySearchStringException());
        case "POLLUTED_SORT_COMMAND_EXCEPTION":
            return new ErrorCommand(new PollutedSortCommandException());
        default:
            return null;
        }
    }

}
