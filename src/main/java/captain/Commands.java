package captain;

public enum Commands {
    LIST("list"),
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event"),
    DONE("done"),
    DELETE("delete"),
    FIND("find"),
    CLEAR("clear"),
    EXIT("exit"),
    INVALID("");

    public final String userCommand;

    Commands(String userCommand) {
        this.userCommand = userCommand;
    }
}
