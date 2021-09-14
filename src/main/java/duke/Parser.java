package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import command.AddCommand;
import command.Command;
import command.DeleteCommand;
import command.DoneCommand;
import command.ExitCommand;
import command.FindCommand;
import command.HelpCommand;
import command.ListCommand;
import task.Deadline;
import task.Event;
import task.Todo;

/**
 * Deals with making sense of the user command.
 *
 * @author felix-ong
 */
public class Parser {

    /**
     * Parses the user input to determine the command.
     *
     * @param userInput Command and description entered by the user.
     * @return Command to be executed.
     * @throws DukeException If missing arguments for commands or unknown commands.
     */
    public static Command parse(String userInput) throws DukeException {
        String[] parts = userInput.split(" ", 2);
        String command = parts[0].toLowerCase();
        int partsLength = parts.length;

        if (command.equals("bye")) {
            return byeCommand();
        } else if (command.equals("list")) {
            return listCommand();
        } else if (command.equals("done")) {
            return doneCommand(partsLength, parts[1]);
        } else if (command.equals("delete")) {
            return deleteCommand(partsLength, parts[1]);
        } else if (command.equals("find")) {
            return findCommand(partsLength, parts[1].toLowerCase());
        } else if (command.equals("help")) {
            return helpCommand();
        } else {
            return addTaskCommand(partsLength, parts);
        }
    }

    private static Command addTaskCommand(int partsLength, String[] parts) throws DukeException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd kkmm");

        switch (parts[0]) {
        case "t":
        case "todo":
            if (partsLength < 2) {
                throw new DukeException("The description of a todo cannot be empty!");
            }
            Todo todo = new Todo(parts[1]);
            return new AddCommand(todo);
        case "d":
        case "deadline":
            if (partsLength < 2) {
                throw new DukeException("The description of a deadline cannot be empty!");
            }
            String[] deadlineParts = parts[1].split(" /by ");
            LocalDateTime deadlineDateTime = LocalDateTime.parse(deadlineParts[1], formatter);
            Deadline deadline = new Deadline(deadlineParts[0], deadlineDateTime);
            return new AddCommand(deadline);
        case "e":
        case "event":
            if (partsLength < 2) {
                throw new DukeException("The description of an event cannot be empty!");
            }
            String[] eventParts = parts[1].split(" /at ");
            LocalDateTime eventDateTime = LocalDateTime.parse(eventParts[1], formatter);
            Event event = new Event(eventParts[0], eventDateTime);
            return new AddCommand(event);
        default:
            throw new DukeException("I'm sorry, but I don't know what that means (X_X)");
        }
    }

    private static Command byeCommand() {
        return new ExitCommand();
    }

    private static Command listCommand() {
        return new ListCommand();
    }

    private static Command findCommand(int partsLength, String keyword) throws DukeException {
        if (partsLength < 2) {
            throw new DukeException("You must provide a keyword.");
        }
        return new FindCommand(keyword);
    }

    private static Command doneCommand(int partsLength, String index) throws DukeException {
        if (partsLength < 2) {
            throw new DukeException("You must provide the index of the task to mark as done.");
        }
        return new DoneCommand(index);
    }

    private static Command deleteCommand(int partsLength, String index) throws DukeException {
        if (partsLength < 2) {
            throw new DukeException("You must provide the index of the task to delete.");
        }
        return new DeleteCommand(index);
    }

    private static Command helpCommand() {
        return new HelpCommand();
    }
}
