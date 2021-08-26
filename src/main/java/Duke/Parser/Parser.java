package Duke.Parser;

import Duke.Commands.*;
import Duke.Commands.Command.CommandType;
import Duke.Errors.DukeError;
import Duke.Parser.Cleaner;
import Duke.Task.*;

import java.time.LocalDate;
import java.util.ArrayList;

public class Parser {

    public static Command parse(String fullCommand, TaskList taskList) {
        Cleaner cl = new Cleaner();
        String cleanCommand = cl.clean(fullCommand, taskList.getCapacity());
        String firstWord = cleanCommand.split(" ")[0];
        switch(firstWord) {
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

    public static Command findParser(String input) {
        String searchString = input.substring(5);
        return new SearchCommand(searchString);
    }

    public static Command todoParser(String input) {
        String description = input.substring(5).strip();
        return new AddCommand(new ToDo(description));
    }

    public static Command eventParser(String input) {
        String withoutEvent = input.substring(6).strip();
        String[] eventArray = withoutEvent.split("/at");
        String description = eventArray[0].strip();
        LocalDate date = CustomDateFormatter.getLocalDateFromString(eventArray[1].strip());
        return new AddCommand(new Event(description, date));
    }

    public static Command deadlineParser(String input) {
        String withoutDeadline = input.substring(9).strip();
        String[] deadlineArray = withoutDeadline.split("/by");
        String description = deadlineArray[0].strip();
        LocalDate date = CustomDateFormatter.getLocalDateFromString(deadlineArray[1].strip());
        return new AddCommand(new Deadline(description, date));
    }

    public static Command markDoneParser(String input) {
        int index = Integer.valueOf(input.substring(5));
        return new EditCommand(CommandType.DONE, index);
    }

    public static Command markUndoParser(String input) {
        int index = Integer.valueOf(input.substring(5));
        return new EditCommand(CommandType.UNDO, index);
    }

    public static Command deleteParser(String input) {
        int index = Integer.valueOf(input.substring(7));
        return new EditCommand(CommandType.DELETE, index);
    }

    public static Command errorParser(String input) {
        int code = Integer.valueOf(input.substring((6)));
        return new ErrorCommand(code);
    }

}
