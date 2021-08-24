package duke;

public enum Command {
    UNRECOGNISED,
    LIST,
    DONE,
    TODO,
    DEADLINE,
    EVENT,
    DELETE,
    BYE,
    FIND;

    public static Command readInput(String input) {
        String newInput = input.toLowerCase();
        Command newCommand;
        switch (newInput) {
            case "list":
                newCommand = Command.LIST;
                break;
            case "done":
                newCommand = Command.DONE;
                break;
            case "todo":
                newCommand = Command.TODO;
                break;
            case "deadline":
                newCommand = Command.DEADLINE;
                break;
            case "event":
                newCommand = Command.EVENT;
                break;
            case "delete":
                newCommand = Command.DELETE;
                break;
            case "bye":
                newCommand = Command.BYE;
                break;
            case "find":
                newCommand = Command.FIND;
                break;
            default:
                newCommand = Command.UNRECOGNISED;
                break;
        }
        return newCommand;
    }
}
