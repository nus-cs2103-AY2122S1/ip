package seedu.duke.commands;

public enum CommandType {
    BYE("bye"),
    LIST("list"),
    DONE("done"),
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event"),
    DELETE("delete"),
    FIND("find"),
    EMPTY("empty"),
    INVALID("invalid");

    private String command;

    private CommandType(String command) {
        this.command = command;
    }

    public String getCommand() {
        return this.command;
    }

    public static CommandType getCommandType(String command) {
        switch(command) {
        case "bye":
            return CommandType.BYE;
        case "list":
            return CommandType.LIST;
        case "done":
            return CommandType.DONE;
        case "todo":
            return CommandType.TODO;
        case "deadline":
            return CommandType.DEADLINE;
        case "event":
            return CommandType.EVENT;
        case "delete":
            return CommandType.DELETE;
        case "find":
            return CommandType.FIND;
        case "":
            return CommandType.EMPTY;
        default:
            return CommandType.INVALID;
        }
    }
}
