package commands;

/**
 * A command that undoes a command that extends UndoableCommand.
 */
public class UndoCommand extends Command {

    /** The stack storing the past commands*/
    private final CommandStack commandStack;

    /**
     * Creates an UndoCommand.
     *
     * @param commandStack The command stack storing the past commands.
     */
    public UndoCommand(CommandStack commandStack) {
        this.commandStack = commandStack;
    }

    @Override
    public boolean execute() {
        UndoableCommand command = this.commandStack.getLastCommand();
        if (command == null) {
            this.setExecutionMessage("Unable to undo");
            return false;
        }
        command.undo();
        this.setExecutionMessage("Undo successful");
        return true;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    protected String getInvalidArgumentsMessage() {
        // Not needed as undo has no required arguments
        return null;
    }
}
