package duke.command;

public class Command {
    private CommandKeyword keyword;
    private String restOfCommand;

    /**
     * Constructs a user command.
     *
     * @param keyword The user command.
     * @param restOfCommand The rest of the command, if any.
     */
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
