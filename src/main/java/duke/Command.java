package duke;

public enum Command {
    DONE("done"),
    LIST("list"),
    DELETE("delete"),
    TODO("todo"),
    EVENT("event"),
    DEADLINE("deadline"),
    BYE("bye"),
    FIND("find"),
    HELP("help"),
    UNKNOWN("unknown");

    private final String value;

    Command(String value) {
        this.value = value;
    }

    public boolean strIsSelf(String input) {
        return (input.equals(value));
    }

}
