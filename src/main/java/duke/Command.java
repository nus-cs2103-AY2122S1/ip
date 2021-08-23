package duke;

public enum Command {
    DONE("done"),
    LIST("list"),
    DELETE("delete"),
    TODO("todo"),
    EVENT("event"),
    DEADLINE("deadline"),
    BYE("bye"),
    UNKNOWN("unknown");

    protected final String value;

    Command(String value) {
        this.value = value;
    }

}