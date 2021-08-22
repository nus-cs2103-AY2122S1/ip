package commands;

import commands.Command;
import duke.Ui;

/**
 * A command to exit duke.
 */
public class ExitCommand implements Command {

    /**
     * Prints a command to say bye to the user and closed the Chat bot.
     */
    @Override
    public void execute() {
        Ui.endChat();
    }

    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public void invalidArgumentsProvided() {
        // Not needed since no arguments are needed for the exit command
    }
}
