public class Command {
    private String[] parsedCommand;
    private String time;

    public Command(String unparsedCommand) {
        String unparsedCommandWithoutTime;
        String[] parsedCommandTemp = unparsedCommand.split(" ", 2);
        boolean isAddCommandType = parsedCommandTemp[0].equals(Saber.InputCommand.add.name());
        boolean isTodoCommandType = parsedCommandTemp[0].equals(Saber.InputCommand.todo.name());
        boolean hasTime = unparsedCommand.contains("/at ") || unparsedCommand.contains("/by ");
        if (hasTime && !isAddCommandType && !isTodoCommandType) {
            int slashIndex = unparsedCommand.indexOf("/at ");
            if (slashIndex == -1) {
                slashIndex = unparsedCommand.indexOf("/by ");
            }
            unparsedCommandWithoutTime = unparsedCommand.substring(0, slashIndex).trim();
            try {
                this.time = unparsedCommand.substring(slashIndex + 4);
            } catch (IndexOutOfBoundsException e) {
                this.time = "";
            }
        } else {
            this.time = "";
            unparsedCommandWithoutTime = unparsedCommand;
        }
        this.parsedCommand = unparsedCommandWithoutTime.split(" ", 2);
    }

    public Saber.InputCommand getCommandType() throws SaberCommandNotFoundException {
        Saber.InputCommand commandType;

        try {
            commandType =  Saber.InputCommand.valueOf(this.parsedCommand[0]);
        }
        catch (IllegalArgumentException e) {
            throw new SaberCommandNotFoundException("Invalid Command");
        }
        return commandType;
    }

    public String getTime() throws MissingTimeException {
        if (this.time.equals("")) {
            throw new MissingTimeException("Time not found");
        }
        return this.time;
    }

    public String getArgument() throws MissingArgumentException {
        if (parsedCommand.length == 1) {
           throw new MissingArgumentException("Argument not found");
        }
        return this.parsedCommand[1];
    }
}