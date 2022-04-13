package duke;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Parser {
    /**
     * the endpoint for parsing the user input.
     *
     * @param input input string from the user.
     * @return the command instance.
     * @throws DukeException thrown when there are error parsing.
     */
    public static Command parse(String input) throws DukeException {
        Action action = getAction(input);
        String argument = getArgument(input);

        String[] descriptionTime;
        LocalDate time;
        Command command;

        switch (action) {
        case CLEAR:
            command = new ClearCommand();
            break;
        case HELP:
            command = new HelpCommand();
            break;
        case SAVE:
            command = new SaveCommand();
            break;
        case LIST:
            command = new ListCommand();
            break;
        case FIND:
            command = new FindCommand(argument);
            break;
        case DONE:
            command = new DoneCommand(parseInt(argument));
            break;
        case DELETE:
            command = new DeleteCommand(parseInt(argument));
            break;
        case TODO:
            Task todo = new Todo(parseDescription(argument));
            command = new AddCommand(action, todo);
            break;
        case DEADLINE:
            descriptionTime = parseDescription(argument, Deadline.TYPE, Deadline.DELIMINATOR);
            time = parseTime(descriptionTime[1]);
            Task deadline = new Deadline(descriptionTime[0], time);
            command = new AddCommand(action, deadline);
            break;
        case EVENT:
            descriptionTime = parseDescription(argument, Event.TYPE, Event.DELIMINATOR);
            time = parseTime(descriptionTime[1]);
            Task event = new Event(descriptionTime[0], time);
            command = new AddCommand(action, event);
            break;
        default:
            throw new UnknownActionException();
        }

        return command;
    }

    private static String getArgument(String input) {
        String action = input.split(" ")[0];
        if (action.length() == input.length()) {
            return "";
        } else {
            return input.substring(action.length() + 1);
        }
    }

    private static Action getAction(String input) {
        String action = input.split(" ")[0].toLowerCase();

        switch (action) {
        case "clear":
            return Action.CLEAR;
        case "help":
            return Action.HELP;
        case "save":
            return Action.SAVE;
        case "list":
            return Action.LIST;
        case "find":
            return Action.FIND;
        case "done":
            return Action.DONE;
        case "delete":
            return Action.DELETE;
        case "todo":
            return Action.TODO;
        case "deadline":
            return Action.DEADLINE;
        case "event":
            return Action.EVENT;
        default:
            return Action.UNKNOWN;
        }
    }

    private static int parseInt(String arg) throws WrongFormatException {
        try {
            return Integer.parseInt(arg);
        } catch (NumberFormatException e) {
            throw new WrongFormatException("done/delete <index for the task>");
        }
    }

    private static String[] parseDescription(String argument, String typeName, String deliminator)
            throws DukeException {
        String[] descriptionTime = argument.split(" " + deliminator + " ");
        if (argument.trim().equals(deliminator) || argument.isBlank()) {
            throw new EmptyDescriptionException();
        } else if (descriptionTime.length < 2) {
            throw new WrongFormatException(typeName + " <description> " + deliminator + " <yyyy-mm-dd>");
        } else if (descriptionTime[0].isBlank() || descriptionTime[1].isBlank()) {
            throw new EmptyDescriptionException();
        } else {
            return descriptionTime;
        }
    }

    private static String parseDescription(String argument) throws EmptyDescriptionException {
        if (argument.isBlank()) {
            throw new EmptyDescriptionException();
        } else {
            return argument;
        }
    }

    private static LocalDate parseTime(String time) throws WrongFormatException {
        try {
            return LocalDate.parse(time);
        } catch (DateTimeParseException e) {
            throw new WrongFormatException("Please enter the correct datetime format <yyyy-mm-dd>");
        }
    }
}
