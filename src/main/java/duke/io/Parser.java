package duke.io;

import duke.Command;
import duke.DukeException;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class Parser {

    /**
     * Returns a Command object based on user input.
     *
     * @param entry String inputted by the user.
     * @return Command object.
     * @throws NumberFormatException If an int is expected to be inputted but the user fails to do so.
     * @throws DateTimeParseException If the user enters the date and time in a wrong format.
     * @throws DukeException If the user enters the command in a wrong format.
     */
    public static Command parse(String entry) throws NumberFormatException, DateTimeParseException, DukeException {
        String main;
        String aux = "";
        if (entry.indexOf(' ') > 0) {
            main = entry.substring(0, entry.indexOf(' '));
            aux = entry.substring(entry.indexOf(' ') + 1);
        } else {
            main = entry;
        }
        switch (main) {
        case "bye":
            return new Command(Command.COMMANDS.BYE);
        case "list":
            return new Command(Command.COMMANDS.LIST);
        case "help":
            return new Command(Command.COMMANDS.HELP);
        case "done":
            return new Command(Command.COMMANDS.DONE, Integer.parseInt(aux));
        case "delete":
            return new Command(Command.COMMANDS.DELETE, Integer.parseInt(aux));
        case "todo":
            return new Command(Command.COMMANDS.TODO, aux);
        case "deadline":
            return new Command(Command.COMMANDS.DEADLINE, aux.split(" /by "));
        case "event":
            return new Command(Command.COMMANDS.EVENT, aux.split(" /at "));
        case "by":
            return new Command(Command.COMMANDS.BY, LocalDateTime.parse(aux));
        case "at":
            return new Command(Command.COMMANDS.AT, LocalDateTime.parse(aux));
        case "all":
            return new Command(Command.COMMANDS.ALL, LocalDateTime.parse(aux));
        default:
            throw new DukeException(DukeException.Type.COMMAND);
        }
    }
}
