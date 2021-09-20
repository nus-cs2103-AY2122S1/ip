package duke;

import java.time.LocalDate;

/**
 * Contains logic and methods relating to parsing user input from CLI.
 */
public class Parser {
    private String currentInput;
    private CommandType currentType;

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
            currentType = CommandType.AddToDo;
            return CommandType.AddToDo;
        case "deadline":
            currentType = CommandType.AddDeadline;
            return CommandType.AddDeadline;
        case "event":
            currentType = CommandType.AddEvent;
            return CommandType.AddEvent;
        case "done":
            return CommandType.MarkAsDone;
        case "find":
            return CommandType.Find;
        default:
            return CommandType.Error;
        }
    }

    public String getSearchTerm() {
        return currentInput.split(" ")[1];
    }

    /**
     * Uses current command type to decide how to parse the description of task.
     *
     * @return Task Either ToDo/Deadline/Event task based on command.
     * @throws DukeException In case of incorrect instruction format.
     */
    public Task generateTask() throws DukeException {
        if (currentType == CommandType.AddToDo) {
            String description = currentInput.split("/by ")[0].substring(5);
            if (description.isBlank()) {
                throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
            }
            return new ToDo(description, false);
        } else if (currentType == CommandType.AddDeadline) {
            String description = currentInput.split("/by ")[0].substring(9);
            String by = currentInput.split("/by ")[1];
            LocalDate deadline  = LocalDate.parse(by);
            if (description.isBlank()) {
                throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
            }
            return new Deadline(description, deadline, false);
        } else if (currentType == CommandType.AddEvent) {
            String description = currentInput.split("/at ")[0].substring(6);
            String by = currentInput.split("/at ")[1];
            LocalDate eventDate  = LocalDate.parse(by);
            if (description.isBlank()) {
                throw new DukeException("OOPS!!! The description of an event cannot be empty.");
            }
            return new Event(description, eventDate, false);
        } else {
            throw new DukeException("Generate task called with wrong command type");
        }
    }

    /**
     * Only called in event of Delete/Done.
     *
     * @return int Index of task to be deleted/marked as complete.
     */
    public int getIndex() {
        return Integer.parseInt(currentInput.split(" ")[1]) - 1;
    }
}
