package duke;

public class UserInput {
    protected String pre_command;
    protected String body_command;

    UserInput(String pre_command, String body_command) {
        this.pre_command = pre_command;
        this.body_command = body_command;
    }

    @Override
    public String toString() {
        return pre_command + body_command;
    }
}
