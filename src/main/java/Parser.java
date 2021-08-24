public class Parser {

    private enum CommandType {
        LIST, DONE, TODO, EVENT, DEADLINE, DELETE
    }

    public static Command parse(String fullCommand) {

        String[] commandComponents = fullCommand.split(" ", 2);
        String commandType = commandComponents[0].toUpperCase();
        Command command;

        switch (CommandType.valueOf(commandType)) {
        case LIST:
            command = new ListCommand();
            break;
        case DONE:
            command = new DoneCommand();
            break;
        case DELETE:
            command = new DeleteCommand();
            break;
        case TODO:
            // Fallthrough
        case EVENT:
            // Fallthrough
        case DEADLINE:
            command = new AddCommand();
            break;
        default:
            command = new UnknownCommand();
            break;
        }

        return command;
    }
}
