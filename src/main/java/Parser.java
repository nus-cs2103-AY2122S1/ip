/** Utility class that handles parsing of strings to other useful data structures. */
public class Parser {
    private static Commands commandParser(String fullCommand) {
        return Commands.valueOfLabel(fullCommand.trim().split(" ")[0]);
    }

    private static String contentParser(String fullCommand) {
        String[] token = fullCommand.trim().split(" ", 2);
        if (token.length < 2) {
            return "";
        }
        return token[1];
    }

    private static void blankCommandChecker(String fullCommand) throws EmptyCommandException {
        if (fullCommand.trim().length() < 1) {
            throw new EmptyCommandException();
        }
    }

    public static Command parse(String fullCommand) throws DukeException {
        blankCommandChecker(fullCommand);
        Command command;
        switch (commandParser(fullCommand)) {
        case EXIT:
            command = ExitCommand.of(contentParser(fullCommand));
            break;
        case LIST:
            command = ListCommand.of(contentParser(fullCommand));
            break;
        case ADD:
            command = AddCommand.of(contentParser(fullCommand));
            break;
        case DONE:
            command = DoneCommand.of(contentParser(fullCommand));
            break;
        case DELETE:
            command = DeleteCommand.of(contentParser(fullCommand));
            break;
        default:
            throw new UnknownCommandException(fullCommand);
        }
        return command;
    }
}
