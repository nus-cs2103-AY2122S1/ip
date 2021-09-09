package duke.main;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.InvalidCommand;
import duke.command.ListCommand;
import duke.command.PriorityCommand;
import duke.command.SaveCommand;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;

/**
 * The Parser class holds static methods which are used to parse and evaluate user commands, ensuring the right command
 * is executed.
 */
public class Parser {

    /**
     * Static method which takes in user commands and ensures the right command is executed in the Duke application.
     *
     * @param command The user command entered.
     * @return Command This returns the Command class that should be executed.
     * @throws DukeException On wrong syntax of command entered.
     */
    public static Command parse(String command) throws DukeException {
        String first = command.toLowerCase().split(" ")[0];
        switch (first) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "todo":
            return parseToDo(command);
        case "deadline":
            return parseDeadline(command);
        case "event":
            return parseEvent(command);
        case "delete":
            return parseDelete(command);
        case "done":
            return parseDone(command);
        case "save":
            return new SaveCommand();
        case "find":
            return parseFind(command);
        case "priority":
            return parsePriority(command);
        default:
            return new InvalidCommand();
        }
    }

    private static Command parseToDo(String command) throws DukeException {
        String errorMessage = "\t Invalid command, description is required, please follow template:\n";
        errorMessage += "\t \t todo {description}";

        if (command.length() < 5) {
            throw new DukeException(errorMessage);
        }

        String description = command.substring(5);
        if (description.isEmpty() || description.isBlank()) {
            throw new DukeException(errorMessage);
        }

        ToDo t = new ToDo(description);
        return new AddCommand(t);
    }

    private static Command parseDeadline(String command) throws DukeException {
        String errorMessage = "\t Invalid command, description and time is required, please follow template:\n";
        errorMessage += "\t \t deadline {description} /by {yyyy-mm-dd}";

        int endOfDescription = command.indexOf("/");
        if (endOfDescription == -1 || command.length() < 9) {
            throw new DukeException(errorMessage);
        }

        // Find substring containing task description
        String description = command.substring(9, endOfDescription);
        if (description.isEmpty() || description.isBlank()) {
            throw new DukeException(errorMessage);
        }

        String time = command.substring(endOfDescription + 1);
        if (isInvalidString(time) || !time.substring(0, 3).equals("by ")) {
            throw new DukeException(errorMessage);
        }

        // Remove the at from time and ensures that there is no additional whitespace behind
        time = time.substring(3, 13);
        if (!isValidDate(time) || isInvalidString(time)) {
            throw new DukeException(errorMessage);
        }


        Deadline t = new Deadline(description, time);
        return new AddCommand(t);
    }

    private static Command parseEvent(String command) throws DukeException {
        String errorMessage = "\t Invalid command, description and time is required, please follow template:\n";
        errorMessage += "\t \t event {description} /at {yyyy-mm-dd}";

        int endOfDescription = command.indexOf("/");
        if (endOfDescription == -1 || command.length() < 6) {
            throw new DukeException(errorMessage);
        }

        // Find substring containing task description
        String description = command.substring(6, endOfDescription);
        if (description.isEmpty() || description.isBlank()) {
            throw new DukeException(errorMessage);
        }

        String time = command.substring(endOfDescription + 1);
        if (isInvalidString(time) || !time.substring(0, 3).equals("at ")) {
            throw new DukeException(errorMessage);
        }

        // Remove the at from time and ensures that there is no additional whitespace behind
        time = time.substring(3, 13);
        if (!isValidDate(time) || isInvalidString(time)) {
            throw new DukeException(errorMessage);
        }

        Event t = new Event(description, time);
        return new AddCommand(t);
    }

    private static Command parseDelete(String command) throws DukeException {
        if (command.length() < 7 || isInvalidString(command.substring(7))) {
            String errorMessage = "\t Invalid command, please key in number of the task to be deleted as follows:\n";
            errorMessage += "\t \t delete {number}";
            throw new DukeException(errorMessage);
        }

        int index = Integer.parseInt(command.substring(7).replaceAll(" ", "")) - 1;

        return new DeleteCommand(index);
    }

    private static Command parseDone(String command) throws DukeException {
        if (command.length() < 5 || isInvalidString(command.substring(5))) {
            String errorMessage = "\t Invalid command, please key in number of the task to be done as follows:\n";
            errorMessage += "\t \t done {number}";
            throw new DukeException(errorMessage);
        }

        int index = Integer.parseInt(command.substring(5).replaceAll(" ", "")) - 1;

        return new DoneCommand(index);
    }

    private static Command parsePriority(String command) throws DukeException {
        if (command.length() < 9 || isInvalidString(command.substring(9))) {
            String errorMessage = "\t Invalid command, please use the command as follows:\n";
            errorMessage += "\t \t priority {number} {priority}";
            throw new DukeException(errorMessage);
        }

        String body = command.substring(9);
        String[] components = body.split(" ");
        int index = Integer.parseInt(components[0].replaceAll(" ", "")) - 1;
        String priority = components[1];

        if (!validPriority(priority)) {
            String errorMessage = "\t Invalid command, please use the command as follows:\n";
            errorMessage += "\t \t priority {number} {priority}";
            throw new DukeException(errorMessage);
        }

        return new PriorityCommand(index, priority);
    }

    private static Command parseFind(String command) throws DukeException {
        if (command.length() < 5 || isInvalidString(command.substring(5))) {
            String errorMessage = "\t Invalid command, please key in the find command as follows:\n";
            errorMessage += "\t \t find {keyword}";
            throw new DukeException(errorMessage);
        }

        String keyword = command.substring(5).toLowerCase();

        return new FindCommand(keyword);
    }

    private static boolean isInvalidString(String s) {
        return s.isBlank() || s.isEmpty();
    }

    private static boolean isValidDate(String date) {
        try {
            LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (DateTimeParseException e) {
            return false;
        }

        return true;
    }

    private static boolean validPriority(String priority) {
        switch (priority.toLowerCase()) {
        case "low":
        case "medium":
        case "high":
            return true;
        default:
            return false;
        }
    }
}
