package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

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
        String[] parts = userInput.split(" ");
        String command = parts[0];
        int partsLength = parts.length;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd kkmm");

        switch (command) {
        case "bye":
            return new ExitCommand();
        case "todo":
            if (partsLength < 2) {
                throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
            }
            Task todo = new Todo(parts[1]);
            return new AddCommand(todo);
        case "deadline":
            if (partsLength < 2) {
                throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
            }
            String by = userInput.split(" /by ")[1];
            LocalDateTime deadlineDateTime = LocalDateTime.parse(by, formatter);
            Task deadline = new Deadline(getDescription(parts, partsLength), deadlineDateTime);
            return new AddCommand(deadline);
        case "event":
            if (partsLength < 2) {
                throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
            }
            String at = userInput.split(" /at ")[1];
            LocalDateTime eventDateTime = LocalDateTime.parse(at, formatter);
            Task event = new Event(getDescription(parts, partsLength), eventDateTime);
            return new AddCommand(event);
        case "list":
            return new ListCommand();
        case "done":
            if (partsLength < 2) {
                throw new DukeException("☹ OOPS!!! You must provide the index of the task to mark as done.");
            }
            return new DoneCommand(parts[1]);
        case "delete":
            if (partsLength < 2) {
                throw new DukeException("☹ OOPS!!! You must provide the index of the task to delete.");
            }
            return new DeleteCommand(parts[1]);
        case "find":
            if (partsLength != 2) {
                throw new DukeException("☹ OOPS!!! You must provide a keyword.");
            }
            return new FindCommand(parts[1]);
        default:
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means (X_X)"
                    + "\nPlease enter one of the following commands:\n todo <task>"
                    + "\n deadline <task> /by <deadline(in yyyy-MM-dd kkmm format)>"
                    + "\n event <event> /at <date(in yyyy-MM-dd kkmm format)>"
                    + "\n list\n bye(to quit)");
        }
    }

    private static String getDescription(String[] parts, int partsLength) {
        return String.join(" ", Arrays.copyOfRange(parts, 1, partsLength - 2));
    }
}
