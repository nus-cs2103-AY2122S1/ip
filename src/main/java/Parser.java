import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

/**
 * Deals with making sense of the user command.
 *
 * @author felix-ong
 */
public class Parser {
    public static Command parseCommand(String userInput) throws DukeException {
        String[] parts = userInput.split(" ");
        String command = parts[0];
        int partsLength = parts.length;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd kkmm");

        switch (command) {
        case "todo":
            if (partsLength < 2) {
                throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
            }
            Task todo = new Todo(getDescription(parts, partsLength));
            return new AddCommand(todo);
        case "deadline":
            if (partsLength < 2) {
                throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
            }
            LocalDateTime by = LocalDateTime.parse(parts[partsLength - 1], formatter);
            Task deadline = new Deadline(getDescription(parts, partsLength), by);
            return new AddCommand(deadline);
        case "event":
            if (partsLength < 2) {
                throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
            }
            LocalDateTime at = LocalDateTime.parse(parts[partsLength - 1], formatter);
            Task event = new Event(getDescription(parts, partsLength), at);
            return new AddCommand(event);
        case "list":
            return new ListCommand();
        case "done":
            return new DoneCommand(parts[1]);
        case "delete":
            return new DeleteCommand(parts[1]);
        default:
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means (X_X)" +
                    "\nPlease enter one of the following commands:\n todo <task>" +
                    "\n deadline <task> /by <deadline(in yyyy-MM-dd kkmm format)>" +
                    "\n event <event> /at <date(in yyyy-MM-dd kkmm format)>" +
                    "\n list\n bye(to quit)");
        }




    }

    private static String getDescription(String[] parts, int partsLength) {
        return String.join(" ", Arrays.copyOfRange(parts, 1, partsLength - 2));
    }
}
