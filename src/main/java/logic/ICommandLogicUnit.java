package logic;

import model.Command;

import java.util.Map;

/**
 * Interface for the central processing unit of the commands that contain the logic and data layer.
 */
public interface ICommandLogicUnit {
    /**
     * Processes commands, in this implementation it accepts all the commands in the enum Command.
     *
     * @param command Command.
     * @param arguments Key-Value pair corresponding to name and value of each argument for the command.
     */
    void processCommand(Command command, Map<String, String> arguments);
}
