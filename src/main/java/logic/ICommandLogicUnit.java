package logic;

import model.Command;

/**
 * Interface for the central processing unit of the commands that contain the logic and data layer.
 */
public interface ICommandLogicUnit {
    /**
     * Processes commands, in this implementation it accepts all the commands in the enum Command.
     *
     * @param command Command.
     * @param arguments CommandArgument.
     */
    void processCommand(Command command, CommandArgument arguments);
}
