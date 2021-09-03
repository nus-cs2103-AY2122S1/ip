package duke;

public enum Commands {
    LIST("list"),
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event"),
    DONE("done"),
    DELETE("delete"),
    FIND("find"),
    INVALID(""),
    EXIT("exit");

    public final String userCommand;

    Commands(String userCommand) {
        this.userCommand = userCommand;
    }
}
