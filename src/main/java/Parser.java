import java.time.LocalDate;

public class Parser {
    public static Command parse(String input) throws DukeException {
        Action action = getAction(input);
        String argument = getArgument(input);
        Command command;
        switch (action) {
        case BYE:
            command = new ExitCommand();
            break;
        case LIST:
            command = new ListCommand();
            break;
        case DONE:
            command = new DoneCommand(parseInt(argument));
            break;
        case DELETE:
            command = new DeleteCommand(parseInt(argument));
            break;
        case TODO:
            Task todo = new Todo(argument);
            command = new AddCommand(action, todo);
            break;
        case DEADLINE:
            Task deadline = new Deadline(argument);
            command = new AddCommand(action, deadline);
            break;
        case EVENT:
            Task event = new Event(argument);
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
        String action = input.split(" ")[0];

        switch (action) {
        case "bye":
            return Action.BYE;
        case "list":
            return Action.LIST;
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
}
