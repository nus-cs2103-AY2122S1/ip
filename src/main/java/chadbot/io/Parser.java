package chadbot.io;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import chadbot.ChadException;
import chadbot.Command;

public class Parser {

    /**
     * Returns a Command object based on user input.
     *
     * @param userRawInput String inputted by the user.
     * @return Command object.
     * @throws NumberFormatException If an int is expected to be inputted but the user fails to do so.
     * @throws DateTimeParseException If the user enters the date and time in a wrong format.
     * @throws ChadException If the user enters the command in a wrong format.
     */
    public static Command parse(String userRawInput) throws NumberFormatException, DateTimeParseException,
            ChadException {
        String userCommandInput = "";
        String auxiliaryInputs = "";
        int indexOfWhitespace = userRawInput.indexOf(' ');
        if (indexOfWhitespace > 0) {
            userCommandInput = userRawInput.substring(0, indexOfWhitespace);
            auxiliaryInputs = userRawInput.substring(indexOfWhitespace + 1);
        } else if (indexOfWhitespace == -1) {
            userCommandInput = userRawInput;
        }
        switch (userCommandInput) {
        case "bye":
            return new Command(Command.Commands.BYE);
        case "find":
            return new Command(Command.Commands.FIND, auxiliaryInputs);
        case "list":
            return new Command(Command.Commands.LIST);
        case "help":
            return new Command(Command.Commands.HELP);
        case "done":
            return new Command(Command.Commands.DONE, Integer.parseInt(auxiliaryInputs));
        case "delete":
            return new Command(Command.Commands.DELETE, Integer.parseInt(auxiliaryInputs));
        case "todo":
            return new Command(Command.Commands.TODO, auxiliaryInputs);
        case "deadline":
            return new Command(Command.Commands.DEADLINE, auxiliaryInputs.split(" /by "));
        case "event":
            return new Command(Command.Commands.EVENT, auxiliaryInputs.split(" /at "));
        case "by":
            return new Command(Command.Commands.BY, LocalDateTime.parse(auxiliaryInputs));
        case "at":
            return new Command(Command.Commands.AT, LocalDateTime.parse(auxiliaryInputs));
        case "all":
            return new Command(Command.Commands.ALL, LocalDateTime.parse(auxiliaryInputs));
        default:
            throw new ChadException(ChadException.Type.COMMAND);
        }
    }
}
