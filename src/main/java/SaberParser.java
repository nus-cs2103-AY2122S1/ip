public class SaberParser {
    private String[] parsedCommand;
    private String time;

    public SaberParser(String unparsedCommand) {
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

    public SaberCommand parse() throws SaberCommandNotFoundException {
        Saber.InputCommand commandType = getCommandType();
        SaberCommand command;

        switch (commandType) {
            case add:
                try {
                    String taskForAdd = getArgument();
                    command = new AddCommand(taskForAdd, false);
                } catch (MissingArgumentException e) {
                    command = new AddCommand("", true);
                }
                break;

            case bye:
                command = new ByeCommand();
                break;

            case done:
                try {
                    int taskIndex = Integer.parseInt(getArgument());
                    command = new DoneCommand(taskIndex - 1, false);
                } catch (MissingArgumentException | NumberFormatException e) {
                    command = new DoneCommand(0, true);
                }
                break;

            case deadline:
                try {
                    String deadlineTask = getArgument();
                    String deadlineTime = getTime();
                    command = new DeadlineCommand(deadlineTask, deadlineTime, false, false);
                } catch (MissingArgumentException e) {
                    command = new DeadlineCommand("", "", true, false);
                } catch (MissingTimeException e) {
                    command = new DeadlineCommand("", "", false, true);
                }
                break;

            case delete:
                try {
                    int taskIndex = Integer.parseInt(getArgument());
                    command = new DeleteCommand(taskIndex - 1, false);
                } catch (MissingArgumentException | NumberFormatException e) {
                    command = new DeleteCommand(0, true);
                }
                break;

            case event:
                try {
                    String eventTask = getArgument();
                    String eventTime = getTime();
                    command = new EventCommand(eventTask, eventTime, false, false);
                } catch (MissingArgumentException e) {
                    command = new EventCommand("", "", true, false);
                } catch (MissingTimeException e) {
                    command = new EventCommand("", "", false, true);
                }
                break;

            case todo:
                try {
                    String taskForTodo = getArgument();
                    command = new TodoCommand(taskForTodo, false);
                } catch (MissingArgumentException e) {
                    command = new TodoCommand("", true);
                }
                break;

            case list:
                command = new ListCommand();
                break;

            default:
                command = null;
        }
        return command;
    }
}