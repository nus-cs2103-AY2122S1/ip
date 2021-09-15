package duke.command;

import duke.TaskList;
import duke.ui.UserInterface;

/**
 * Represents a Command object
 * @author Gabriel Goh
 */

public abstract class Command {
    private boolean hasMoreCommands;

    /**
     * Executes the command on TaskList, writing any output to
     * the input UserInterface.
     * @param taskList The TaskList which this command is to be executed on
     * @param ui       The UserInterface which any output of this command is to be written to
     */
    public abstract void execute(TaskList taskList, UserInterface ui);

    /**
     * Creates a command object which expects more commands after execution
     */
    public Command() {
        hasMoreCommands = true;
    }

    /**
     * Creates a command object
     * @param hasMoreCommands Whether more Commands are expected to run after this Command or not
     */
    public Command(boolean hasMoreCommands) {
        this.hasMoreCommands = hasMoreCommands;
    }

    /**
     * Returns a boolean, indicating if a Command should be executed after this Command.
     * @return Whether a Command should be executed after this Command or not
     */
    public boolean shouldExecuteNext() {
        return hasMoreCommands;
    }
}
