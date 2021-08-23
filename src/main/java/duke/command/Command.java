package duke.command;

public class Command {
    private CommandKeyword keyword;
    private String restOfCommand;
    public Command(CommandKeyword keyword, String restOfCommand) {
        this.keyword = keyword;
        this.restOfCommand = restOfCommand;
    }

    public CommandKeyword getKeyword() {
        return this.keyword;
    }

    public String getRestOfCommand() {
        return this.restOfCommand;
    }
}
