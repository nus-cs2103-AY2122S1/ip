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
     * @param answer User input parsed into a String.
     * @return Command that is to be executed by Duke based on user input.
     * @throws DukeException If user input is not in the correct format or not a recognised command.
     */
    public static Command parse(String answer) throws DukeException {
        if (answer == null) {
            throw new DukeException("User input is null.");
        }
        
        String[] parts = answer.split(" ");
        String command = parts[0];
        String taskDetails = "";
        if (answer.contains(" ")) {
            taskDetails = answer.substring(answer.indexOf(" ") + 1);
        }
        switch (command) {
        case "done":
            return parseDoneCommand(answer);
        case "delete":
            return parseDeleteCommand(answer);
        case "list":
            return parseListCommand();
        case "todo":
            return parseTodoCommand(taskDetails);
        case "event":
            return parseEventCommand(taskDetails);
        case "deadline":
            return parseDeadlineCommand(taskDetails);
        case "find":
            return parseFindCommand(taskDetails); 
        case "edit":
            return parseEditCommand(answer);
        case "bye":
            return parseByeCommand();
        default:
            throw new DukeException("Unknown command.");
        }
    }

    private static EditCommand parseEditCommand(String taskDetails) throws DukeException {
        int taskIndex = getTaskIndex(taskDetails);
        String[] editDescParts = taskDetails.split("/desc", 2);
        String[] editDateParts = taskDetails.split("/date", 2);
        if (editDescParts.length < 2 && editDateParts.length < 2) {
            throw new DukeException("Nothing to edit.");
        }
        
        if (editDateParts.length >= 2 && editDescParts.length >= 2) {
            return new EditCommand(taskIndex, editDescParts[1].trim(), editDateParts[1].trim());
        } else if (editDateParts.length >= 2) {
            return new EditCommand(taskIndex, null, editDateParts[1].trim());
        } else {
            return new EditCommand(taskIndex, editDescParts[1].trim(), null);
        }
    }

    private static void checkEmptyTaskDetails(String taskDetails) throws DukeException {
        if (taskDetails.isEmpty()) {
            throw new DukeException("Task details cannot be empty");
        }
    }

    private static AddTaskCommand parseTodoCommand(String taskDetails) throws DukeException {
        checkEmptyTaskDetails(taskDetails);
        Todo todo = new Todo(taskDetails);
        return new AddTaskCommand(todo);
    }

    private static AddTaskCommand parseEventCommand(String taskDetails) throws DukeException {
        checkEmptyTaskDetails(taskDetails);
        String[] parts = taskDetails.split(" /at ");
        if (parts.length < 2) {
            throw new DukeException("Event descriptions must contain /at [dd-mm-yyyy hh:mm]");
        }
        String description = parts[0];
        String at = parts[1];
        Event event = new Event(description, at);
        return new AddTaskCommand(event);
    }

    private static AddTaskCommand parseDeadlineCommand(String taskDetails) throws DukeException {
        checkEmptyTaskDetails(taskDetails);
        String[] parts = taskDetails.split(" /by ");
        if (parts.length < 2) {
            throw new DukeException("Deadline descriptions must contain /by [dd-mm-yyyy hh:mm]");
        }
        String description = parts[0];
        String by = parts[1];
        Deadline deadline = new Deadline(description, by);
        return new AddTaskCommand(deadline);
    }

    private static int getTaskIndex(String answer) throws DukeException {
        try {
            String taskNo = answer.split(" ")[1];
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
    
    private static DoneCommand parseDoneCommand(String answer) throws DukeException {
        int taskIndex = getTaskIndex(answer);
        return new DoneCommand(taskIndex);
    }
    
    private static DeleteCommand parseDeleteCommand(String answer) throws DukeException {
        int taskIndex = getTaskIndex(answer);
        return new DeleteCommand(taskIndex);
    }
    
    private static ListCommand parseListCommand() {
        return new ListCommand();
    }
    
    private static FindTasksCommand parseFindCommand(String taskDetails) throws DukeException {
        checkEmptyTaskDetails(taskDetails);
        return new FindTasksCommand(taskDetails);
    }
    
    private static ByeCommand parseByeCommand() {
        return new ByeCommand();
    }
}
