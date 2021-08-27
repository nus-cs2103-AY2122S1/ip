package duke;

import java.time.LocalDate;
import java.util.Scanner;

public class Parser {
    private final Scanner scanner;
    private String currentCommand;
    private CommandType currentType;

    public Parser() {
        scanner = new Scanner(System.in);
    }

    public CommandType nextCommand() {
        currentCommand = scanner.nextLine();
        String command = currentCommand.split(" ")[0];
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
        return currentCommand.split(" ")[1];
    }

    public Task generateTask() throws DukeException {
        if (currentType == CommandType.AddToDo) {
            String description = currentCommand.split("/by ")[0].substring(5);
            if (description.isBlank()) {
                throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
            }
            return new ToDo(description, false);
        } else if (currentType == CommandType.AddDeadline) {
            String description = currentCommand.split("/by ")[0].substring(9);
            String by = currentCommand.split("/by ")[1];
            LocalDate deadline  = LocalDate.parse(by);
            if (description.isBlank()) {
                throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
            }
            return new Deadline(description, deadline, false);
        } else if (currentType == CommandType.AddEvent) {
            String description = currentCommand.split("/at ")[0].substring(6);
            String by = currentCommand.split("/at ")[1];
            LocalDate eventDate  = LocalDate.parse(by);
            if (description.isBlank()) {
                throw new DukeException("OOPS!!! The description of an event cannot be empty.");
            }
            return new Event(description, eventDate, false);
        } else {
            throw new DukeException("Generate task called with wrong command type");
        }
    }

    public int getIndex() {
        return Integer.parseInt(currentCommand.split(" ")[1]) - 1;
    }
}
