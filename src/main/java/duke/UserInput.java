package duke;

public class UserInput {
    protected String preCommand;
    protected String bodyCommand;

    UserInput(String preCommand, String bodyCommand) {
        this.preCommand = preCommand;
        this.bodyCommand = bodyCommand;
    }

    @Override
    public String toString() {
        return preCommand + bodyCommand;
    }
}
