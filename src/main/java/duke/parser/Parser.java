package duke.parser;

import duke.command.Command;
import duke.command.CommandAdd;
import duke.command.CommandDelete;
import duke.command.CommandDone;
import duke.command.CommandExit;
import duke.command.CommandFind;
import duke.command.CommandList;
import duke.command.CommandUnknown;
import duke.exception.DukeException;

/**
 * Deals with the input given by the user and making sense of it.
 */
public class Parser {
    private static final String ADD_FORMAT = "Please enter the `%s` task in this format:\n"
            + "%s <task_description> /%s <date_and_time>";
    private static final String INSUFFICIENT_INPUT_ADD = "Insufficient input received!"
            + " Please add in description of the `%s` task.";
    private static final String INSUFFICIENT_INPUT_D = "Insufficient input received! "
            + "Please indicate the task number of the %s";
    private static final String INVALID_INPUT_ADD = "Invalid input! Please add in %s";
    private static final String INVALID_INPUT_D = "Invalid input! Please enter the task number after '%s'.";

    /**
     * Processes the input given by the user.
     * Returns the corresponding Command afterwards.
     *
     * @param input String representation of the input given by user.
     * @return The corresponding Command.
     * @throws DukeException Throw DukeException.
     */
    public static Command parse(String input) throws DukeException {
        input = input.strip();
        String[] inputs = input.split(" ");
        assert !inputs[0].equals("") : "input should not be empty!";
        String command = inputs[0];

        switch (command.toLowerCase()) {
        case "bye":
            return new CommandExit();
        case "list":
            return new CommandList();
        case "done":
            ParseError doneError = checkForError(input);
            if (doneError == ParseError.INSUFFICIENT_INPUT) {
                throw new DukeException(String.format(INSUFFICIENT_INPUT_D, "completed task."));
            } else if (doneError == ParseError.INVALID_INPUT) {
                throw new DukeException(String.format(INVALID_INPUT_D, "done"));
            } else {
                int index = Integer.parseInt(inputs[1]);

                return new CommandDone(index);
            }
        case "delete":
            ParseError deleteError = checkForError(input);
            if (deleteError == ParseError.INSUFFICIENT_INPUT) {
                throw new DukeException(String.format(INSUFFICIENT_INPUT_D, "task you wish to delete."));
            } else if (deleteError == ParseError.INVALID_INPUT) {
                throw new DukeException(String.format(INVALID_INPUT_D, "delete"));
            } else {
                int index = Integer.parseInt(inputs[1]);

                return new CommandDelete(index);
            }
        case "find":
            ParseError findError = checkForError(input);
            if (findError == ParseError.INSUFFICIENT_INPUT) {
                throw new DukeException("Insufficient input received! Please add in keyword after 'find'.");
            } else {
                String keyword = input.substring(5);
                return new CommandFind(keyword);
            }
        case "todo":
            ParseError tError = checkForErrorAdd(input);
            if (tError == ParseError.INSUFFICIENT_INPUT) {
                throw new DukeException(String.format(INSUFFICIENT_INPUT_ADD, "Todo"));
            } else {
                int tFirst = input.indexOf(" ");
                String tDescription = input.substring(tFirst + 1);

                return new CommandAdd(0, tDescription);
            }
        case "deadline":
            ParseError dError = checkForErrorAdd(input);
            if (dError == ParseError.INSUFFICIENT_INPUT) {
                throw new DukeException(String.format(INSUFFICIENT_INPUT_ADD, "Deadline"));
            } else if (dError == ParseError.INVALID_INPUT) {
                throw new DukeException(String.format(INVALID_INPUT_ADD, "the deadline for the task."));
            } else if (dError == ParseError.WRONG_FORMAT) {
                throw new DukeException(String.format(ADD_FORMAT, "Deadline", "Deadline", "by"));
            } else {
                int dFirst = input.indexOf(" ");
                int dSecond = input.indexOf("/");

                String dDescription = input.substring(dFirst + 1, dSecond - 1);
                String by = input.substring(dSecond + 4);

                return new CommandAdd(1, dDescription, by);
            }
        case "event":
            ParseError eError = checkForErrorAdd(input);
            if (eError == ParseError.INSUFFICIENT_INPUT) {
                throw new DukeException(String.format(INSUFFICIENT_INPUT_ADD, "Event"));
            } else if (eError == ParseError.INVALID_INPUT) {
                throw new DukeException(String.format(INVALID_INPUT_ADD, "information about the event."));
            } else if (eError == ParseError.WRONG_FORMAT) {
                throw new DukeException(String.format(ADD_FORMAT, "Event", "Event", "at"));
            } else {
                int eFirst = input.indexOf(" ");
                int eSecond = input.indexOf("/");

                String eDescription = input.substring(eFirst + 1, eSecond - 1);
                String at = input.substring(eSecond + 4);

                return new CommandAdd(2, eDescription, at);
            }
        default:
            return new CommandUnknown();
        }
    }

    enum ParseError {
        INSUFFICIENT_INPUT,
        INVALID_INPUT,
        WRONG_FORMAT
    }

    private static ParseError checkForErrorAdd(String input) {
        String[] inputs = input.split(" ");
        if (inputs.length == 1) {
            return ParseError.INSUFFICIENT_INPUT;
        }

        if (!input.contains("/by") && !input.contains("/at")) {
            return ParseError.INVALID_INPUT;
        }

        try {
            int first = input.indexOf(" ");
            int second = input.indexOf("/");

            String description = input.substring(first + 1, second - 1);
            String additionalInfo = input.substring(second + 4);

            return null;
        } catch (StringIndexOutOfBoundsException e) {
            return ParseError.WRONG_FORMAT;
        }
    }

    private static ParseError checkForError(String input) {
        String[] inputs = input.split(" ");
        if (inputs.length == 1) {
            return ParseError.INSUFFICIENT_INPUT;
        }

        try {
            int index = Integer.parseInt(inputs[1]);

            return null;
        } catch (NumberFormatException e) {
            return ParseError.INVALID_INPUT;
        }
    }
}
