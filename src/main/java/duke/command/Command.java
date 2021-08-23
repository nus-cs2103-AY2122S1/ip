package duke.command;

import duke.task.TaskHandler;
import duke.ui.Ui;

public abstract class Command {

    boolean mustExit;

    public Command(String command) throws DukeInvalidCommandException {
        this.mustExit = false;
        String[] tokens = command.strip().split(" ");
        if (tokens.length == 0 || tokens[0].length() == 0) {
            throw new DukeInvalidCommandException("This command is empty.");
        }
        String commandName = tokens[0];
        CommandType commandType = getCommandType();
        if (!commandName.equals(commandType.getCommandName())) {
            throw new DukeInvalidCommandException(String.format("This command is not \"%s\".", commandType.getCommandDescription()));
        }
        parseCommand(tokens);
    }

    public abstract void execute(TaskHandler taskHandler, Ui ui) throws DukeInvalidCommandException;

    abstract void parseCommand(String[] tokens) throws DukeInvalidCommandException;

    abstract CommandType getCommandType();

    public boolean mustExit() {
        return mustExit;
    }
}
