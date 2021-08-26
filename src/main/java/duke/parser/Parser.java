package duke.parser;

import duke.commands.*;
import duke.commands.Command.CommandType;
import duke.task.*;

import java.time.LocalDate;

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
        default:
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
     * @param input The cleaned user input
     * @return The appropriate Command object
     */
    public static Command errorParser(String input) {
        int code = Integer.parseInt(input.substring((6)));
        return new ErrorCommand(code);
    }

}
