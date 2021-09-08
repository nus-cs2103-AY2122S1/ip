package duke.commands;

public class InvalidCommand extends Command {

    private String errorMessage;

    public InvalidCommand(String s) {
        errorMessage = s;
    }

    @Override
    public CommandResult execute() {
        return new CommandResult(this.errorMessage);
    }
}
