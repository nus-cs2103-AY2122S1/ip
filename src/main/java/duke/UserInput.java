package duke;

public class UserInput {
    protected static String pre_command;
    protected static String body_command;

    @Override
    public String toString() {
        return pre_command + body_command;
    }
}
