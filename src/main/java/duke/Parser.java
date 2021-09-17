package duke;

import duke.commands.AddTaskCommand;
import duke.commands.ByeCommand;
import duke.commands.Command;
import duke.commands.DeleteCommand;
import duke.commands.DoneCommand;
import duke.commands.EditCommand;
import duke.commands.FindTasksCommand;
import duke.commands.ListCommand;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Todo;

/**
 * This class represents a Parser used to parse user input and return Commands that can be executed by Duke.
 */
public class Parser {
    /**
     * Parses user input into a Command that should be executed by the Duke chatbot.
     *
     * @param input User input parsed into a String.
     * @return Command that is to be executed by Duke based on user input.
     * @throws DukeException If user input is not in the correct format or not a recognised command.
     */
    public static Command parse(String input) throws DukeException {
        if (input == null) {
            throw new DukeException("User input is null.");
        }

        String command = getCommand(input);
        String commandDetails = getCommandDetails(input);

        switch (command) {
        case "done":
            return parseDoneCommand(commandDetails);
        case "delete":
            return parseDeleteCommand(commandDetails);
        case "list":
            return parseListCommand();
        case "todo":
            return parseTodoCommand(commandDetails);
        case "event":
            return parseEventCommand(commandDetails);
        case "deadline":
            return parseDeadlineCommand(commandDetails);
        case "find":
            return parseFindCommand(commandDetails);
        case "edit":
            return parseEditCommand(commandDetails);
        case "bye":
            return parseByeCommand();
        default:
            throw new DukeException("Unknown command.");
        }
    }

    private static String getCommand(String input) {
        String[] parts = input.split(" ");
        return parts[0];
    }

    private static String getCommandDetails(String input) {
        String commandDetails = "";
        if (input.contains(" ")) {
            commandDetails = input.substring(input.indexOf(" ") + 1);
        }
        return commandDetails;
    }

    private static EditCommand parseEditCommand(String commandDetails) throws DukeException {
        final String EDIT_USAGE = "Usage of edit command:\nedit [taskIndex] /desc [new desc] "
                + "/date [new date]\neg. edit 1 /desc Submit IP /date 17-9-2021 23:59";
        int taskIndex = getTaskIndex(commandDetails);
        try {
            String detailsWithoutIndex = commandDetails.split(" ", 2)[1];
            String[] editDescParts = detailsWithoutIndex.split("/desc", 2);
            String[] editDateParts = detailsWithoutIndex.split("/date", 2);
            if (editDescParts.length < 2 && editDateParts.length < 2) {
                throw new DukeException(EDIT_USAGE);
            }

            if (editDateParts.length >= 2 && editDescParts.length >= 2) {
                // both description and date should be edited
                String editedDescription = editDescParts[1].split("/date")[0].trim();
                checkEmptyDetails(editedDescription, "desc");
                String editedDate = editDateParts[1].split("/desc")[0].trim();
                return new EditCommand(taskIndex, editedDescription, editedDate);
            } else if (editDateParts.length >= 2) {
                // only date should be edited
                String editedDate = editDateParts[1].trim();
                return new EditCommand(taskIndex, null, editedDate);
            } else {
                // only description should be edited
                String editedDescription = editDescParts[1].trim();
                checkEmptyDetails(editedDescription, "desc");
                return new EditCommand(taskIndex, editedDescription, null);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException(EDIT_USAGE);
        }
    }

    private static void checkEmptyDetails(String commandDetails, String item) throws DukeException {
        if (commandDetails.isEmpty()) {
            throw new DukeException(item + " details cannot be empty.");
        }
    }

    private static AddTaskCommand parseTodoCommand(String commandDetails) throws DukeException {
        checkEmptyDetails(commandDetails, "Todo");
        Todo todo = new Todo(commandDetails);
        return new AddTaskCommand(todo);
    }

    private static AddTaskCommand parseEventCommand(String commandDetails) throws DukeException {
        String[] parts = commandDetails.split("/at");
        String description = parts[0].trim();
        checkEmptyDetails(description, "Event");
        
        if (parts.length < 2) {
            throw new DukeException("Event descriptions must contain /at [dd-mm-yyyy hh:mm]");
        }
        String at = parts[1].trim();
        
        Event event = new Event(description, at);
        return new AddTaskCommand(event);
    }

    private static AddTaskCommand parseDeadlineCommand(String commandDetails) throws DukeException {
        String[] parts = commandDetails.split("/by");
        String description = parts[0].trim();
        checkEmptyDetails(description, "Deadline");
        
        if (parts.length < 2) {
            throw new DukeException("Deadline descriptions must contain /by [dd-mm-yyyy hh:mm]");
        }
        String by = parts[1].trim();
        
        Deadline deadline = new Deadline(description, by);
        return new AddTaskCommand(deadline);
    }

    private static int getTaskIndex(String commandDetails) throws DukeException {
        try {
            String taskNo = commandDetails.split(" ")[0];
            int taskIndex = Integer.parseInt(taskNo) - 1;
            if (taskIndex < 0) {
                throw new DukeException("Invalid task number. Task number should be positive.");
            }
            return taskIndex;
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Invalid task number. Sample input with correct format: [command] [taskNo]"
                    + " eg. 'done 2'");
        }
    }

    private static DoneCommand parseDoneCommand(String commandDetails) throws DukeException {
        int taskIndex = getTaskIndex(commandDetails);
        return new DoneCommand(taskIndex);
    }

    private static DeleteCommand parseDeleteCommand(String commandDetails) throws DukeException {
        int taskIndex = getTaskIndex(commandDetails);
        return new DeleteCommand(taskIndex);
    }

    private static ListCommand parseListCommand() {
        return new ListCommand();
    }

    private static FindTasksCommand parseFindCommand(String commandDetails) throws DukeException {
        checkEmptyDetails(commandDetails, "Find keyword");
        return new FindTasksCommand(commandDetails);
    }

    private static ByeCommand parseByeCommand() {
        return new ByeCommand();
    }
}
