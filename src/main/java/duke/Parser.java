package duke;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Contains logic and methods relating to parsing user input from CLI.
 */
public class Parser {
    private String currentInput;
    private CommandType currentCommandType;

    /**
     * Matches first word of line of input with current available commands.
     *
     * @return CommandType The command type of the next line of input
     */
    public CommandType nextCommand(String input) {
        this.currentInput = input;
        String command = currentInput.split(" ")[0];
        switch (command) {
        case "bye":
            return CommandType.Exit;
        case "delete":
            return CommandType.DeleteTask;
        case "list":
            return CommandType.List;
        case "todo":
            currentCommandType = CommandType.AddToDo;
            return CommandType.AddToDo;
        case "deadline":
            currentCommandType = CommandType.AddDeadline;
            return CommandType.AddDeadline;
        case "event":
            currentCommandType = CommandType.AddEvent;
            return CommandType.AddEvent;
        case "done":
            return CommandType.MarkAsDone;
        case "find":
            return CommandType.Find;
        default:
            return CommandType.Error;
        }
    }

    /**
     * Returns the search key entered by user.
     *
     * @return String object of search term.
     */
    public String getSearchTerm() {
        assert Objects.equals(currentInput.split(" ")[0], "find") : "Command should be find";
        return currentInput.split(" ")[1];
    }

    /**
     * Uses current command type to decide how to parse the description of task.
     *
     * @return Task Either ToDo/Deadline/Event task based on command.
     * @throws DukeException In case of incorrect instruction format.
     */
    public Task generateNewTask() throws DukeException {
        if (currentCommandType == CommandType.AddToDo) {
            String description = currentInput.substring(5);
            assert Objects.equals(currentInput.split(" ")[0], "todo") : "Command should be todo";
            descriptionCheck(description);
            return new ToDo(description, false);
        } else if (currentCommandType == CommandType.AddDeadline) {
            String description = currentInput.split("/by ")[0].substring(9);
            assert Objects.equals(currentInput.split(" ")[0], "deadline") : "Command should be deadline";
            String by = currentInput.split("/by ")[1];
            LocalDate deadline  = LocalDate.parse(by);
            descriptionCheck(description);
            return new Deadline(description, deadline, false);
        } else if (currentCommandType == CommandType.AddEvent) {
            String description = currentInput.split("/at ")[0].substring(6);
            assert Objects.equals(currentInput.split(" ")[0], "event") : "Command should be event";
            String by = currentInput.split("/at ")[1];
            LocalDate eventDate  = LocalDate.parse(by);
            descriptionCheck(description);
            return new Event(description, eventDate, false);
        } else {
            throw new DukeException("Generate task called with wrong command type");
        }
    }

    private void descriptionCheck(String description) throws DukeException {
        if (description.isBlank()) {
            throw new DukeException("OOPS!!! The description of a task cannot be empty.");
        }
    }

    /**
     * Returns index of task indicated by the user.
     *
     * @return int Index of task to be deleted/marked as complete.
     */
    public int getIndex() {
        assert Objects.equals(currentInput.split(" ")[0], "delete") ||
                Objects.equals(currentInput.split(" ")[0], "done")
                : "Command should be delete/done";
        return Integer.parseInt(currentInput.split(" ")[1]) - 1;
    }
}
