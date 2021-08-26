package duke;

/**
 * The commands supported by Duke
 */
public enum Command {
    LIST("list"), DONE("done"), TODO("todo"),
    DEADLINE("deadline"), EVENT("event"), DELETE("delete");

    public final String command;

    Command(String command) {
        this.command = command;
    }
}
