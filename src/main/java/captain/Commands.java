package captain;

public enum Commands {
    LIST("list"),
    ADD("add"),
    DONE("done"),
    DELETE("delete"),
    FIND("find"),
    CLEAR("clear"),
    SORT("sort"),
    EXIT("exit"),
    INVALID("");

    public final String userCommand;

    Commands(String userCommand) {
        this.userCommand = userCommand;
    }
}
