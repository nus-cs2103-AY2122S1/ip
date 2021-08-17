public class Command {
    private String[] parsedCommand;

    public Command(String unparsedCommand) {
        this.parsedCommand = unparsedCommand.split(" ", 2);
    }

    public Saber.InputCommand getCommandType() {
        return Saber.InputCommand.valueOf(this.parsedCommand[0]);
    }

    public String getArgument() {
        return this.parsedCommand[1];
    }
}
