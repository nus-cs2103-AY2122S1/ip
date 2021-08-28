package duke.io;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import duke.Command;
import duke.DukeException;

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
        case "find":
            return new Command(Command.Commands.FIND, aux);
        case "list":
            return new Command(Command.Commands.LIST);
        case "help":
            return new Command(Command.Commands.HELP);
        case "done":
            return new Command(Command.Commands.DONE, Integer.parseInt(aux));
        case "delete":
            return new Command(Command.Commands.DELETE, Integer.parseInt(aux));
        case "todo":
            return new Command(Command.Commands.TODO, aux);
        case "deadline":
            return new Command(Command.Commands.DEADLINE, aux.split(" /by "));
        case "event":
            return new Command(Command.Commands.EVENT, aux.split(" /at "));
        case "by":
            return new Command(Command.Commands.BY, LocalDateTime.parse(aux));
        case "at":
            return new Command(Command.Commands.AT, LocalDateTime.parse(aux));
        case "all":
            return new Command(Command.Commands.ALL, LocalDateTime.parse(aux));
        default:
            throw new DukeException(DukeException.Type.COMMAND);
        }
    }
}
