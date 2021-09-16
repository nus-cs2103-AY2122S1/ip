package duke;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Deals with making sense of the user's command.
 * It takes in user inputs and filters it to its respective command.
 */
public class Parser {

    static final int DATE_PARSE_INDEX = 4;

    /**
     * Parse the user input to decide what command to execute.
     * 
     * @param command Command is the user input.
     */
    public Command parse(String command) {
        String[] commandArr = command.split(" ");
        int commandArrLength = commandArr.length;
        boolean wrongArrayLength = commandArrLength <= 1;
        switch (command) {
        case "save":
            return new SaveCommand();
        case "list":
            return new ListCommand();
        case "statistics":
            return new StatisticsCommand();
        case "help":
            return new HelpCommand();
        }
        if (wrongArrayLength) {
            return new ErrorCommand("Fix your command!\n" +
                    "Type \"help\" if you need help.");
        }
        switch (commandArr[0]) {
        case "done": 
        case "delete": 
            return parseNonAddingCommand(command);
        case "find":
            String keyword = commandArr[1];
            return new FindCommand(keyword);

        case "todo":
            return parseTodoCommand(command);

        case "deadline":
            return parseDeadlineCommand(command);

        case "event":
            return parseEventCommand(command);

        default:
            return new ErrorCommand("Fix your command!\n" +
                    "Type \"help\" if you need help.");
        }


    }

    /**
     * Parse Commands such as Delete and Done that mutate existing tasks. 
     * 
     * @param command
     * @return
     */
    Command parseNonAddingCommand(String command) {
        String[] commandArr = command.split(" ");
        try {
            int taskArrRef = Integer.parseInt(commandArr[1]) - 1;
            switch (commandArr[0]) {
            case "done":
                return new DoneCommand(taskArrRef);
            case "delete":
                return new DeleteCommand(taskArrRef);
            }
        } catch (NumberFormatException e) {
            return new ErrorCommand("Enter an index number");
        }
        return new ErrorCommand("I don't know what that means");
    }

    /**
     * Parse TodoCommand specifically.
     * 
     * @param command
     * @return
     */
    Command parseTodoCommand(String command) {
        int spaceIndex = command.indexOf(" ");
        String description = command.substring(spaceIndex + 1);
        int descriptionLength = description.length();
        assert descriptionLength > 0 : "Todo Description shouldn't be empty";
        Task task = new Todo(description);
        return new TodoCommand(task);
    }

    /**
     * Parse DeadlineCommand specifically.
     * 
     * @param command
     * @return
     */
    Command parseDeadlineCommand(String command) {
        int byIndex = command.indexOf("/by ");
        int spaceIndex = command.indexOf(" ");
        if (!command.contains("/by ")) {
            return new ErrorCommand("Enter deadline in this format:\""
                    + "[deadline] [task] /by [date]\"");
        } else if (!canParseDescription(command, spaceIndex, byIndex)) {
            return new ErrorCommand("Invalid description");
        }
        
        String description = command.substring(spaceIndex + 1, byIndex - 1);
        assert DATE_PARSE_INDEX == 4 : "DATE_PARSE_INDEX should be 4";
        String date = command.substring(byIndex + DATE_PARSE_INDEX);
        if (!canParseDate(date)) {
            return new ErrorCommand("Wrong date input");
        }
        Task task = new Deadline(description, date);
        return new DeadlineCommand(task);
    }

    /**
     * Parse EventCommand specifically.
     * 
     * @param command
     * @return
     */
    Command parseEventCommand(String command) {
        int spaceIndex = command.indexOf(" ");
        int atIndex = command.indexOf("/at ");
        if (!command.contains("/at ")) {
            return new ErrorCommand("Enter event in this format:\""
                    + "[event] [task] /at [date]\"");
        } else if (!canParseDescription(command, spaceIndex, atIndex)) {
            return new ErrorCommand("Invalid description");
        }
        String description = command.substring(spaceIndex + 1, atIndex - 1);
        assert DATE_PARSE_INDEX == 4 : "DATE_PARSE_INDEX should be 4";
        String date = command.substring(atIndex + DATE_PARSE_INDEX);
        if (!canParseDate(date)) {
            return new ErrorCommand("Wrong date input");
        }
        Task task = new Event(description, date);
        return new EventCommand(task);
    }

    /**
     * Determines whether date input is correct.
     * 
     * @param date
     * @return
     */
    boolean canParseDate(String date) {
        try {
            LocalDate.parse(date);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    /**
     * Determines whether description input is correct.
     * 
     * @param command
     * @param spaceIndex
     * @param dateIndex
     * @return
     */
    boolean canParseDescription(String command, int spaceIndex, int dateIndex) {
        try {
            command.substring(spaceIndex + 1, dateIndex - 1);
            return true;
        } catch (StringIndexOutOfBoundsException e) {
            return false;
        }
    }
        

}
