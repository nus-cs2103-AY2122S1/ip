package duke;

public enum Command {
    LIST("list"), DONE("done"), DELETE("delete"), FIND("find"),
    TODO("todo"), DEADLINE("deadline"), EVENT("event");

    public final String command;

    Command(String command) {
        this.command = command;
    }
}
