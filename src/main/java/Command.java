public class Command {
    private String[] parsedCommand;
    private String time;

    public Command(String unparsedCommand) {
        String unparsedCommandWithoutTime;

        if (unparsedCommand.contains("/")) {
            int slashIndex = unparsedCommand.indexOf("/");
            unparsedCommandWithoutTime = unparsedCommand.substring(0, slashIndex);
            this.time = unparsedCommand.substring(slashIndex + 4);
        } else {
            this.time = "";
            unparsedCommandWithoutTime = unparsedCommand;
        }
        this.parsedCommand = unparsedCommandWithoutTime.split(" ", 2);
    }

    public Saber.InputCommand getCommandType() {
        return Saber.InputCommand.valueOf(this.parsedCommand[0]);
    }

    public String getTime() {
        return this.time;
    }

    public String getArgument() {
        return this.parsedCommand[1];
    }
}