package iris.command;

public enum CommandWord {
    LIST,
    TODO,
    DEADLINE,
    EVENT,
    DONE,
    DELETE,
    FIND,
    BYE,
    INVALID;

    public static CommandWord getCommandWord(String fullCommand) {
        String command = fullCommand.split(" ")[0];

        switch (command) {
        case "list":
            return CommandWord.LIST;
        case "todo":
        case "todo!":
        case "todo!!":
            return CommandWord.TODO;
        case "deadline":
        case "deadline!":
        case "deadline!!":
            return CommandWord.DEADLINE;
        case "event":
        case "event!":
        case "event!!":
            return CommandWord.EVENT;
        case "done":
            return CommandWord.DONE;
        case "delete":
            return CommandWord.DELETE;
        case "find":
            return CommandWord.FIND;
        case "bye":
            return CommandWord.BYE;
        default:
            return CommandWord.INVALID;
        }
    }
}
