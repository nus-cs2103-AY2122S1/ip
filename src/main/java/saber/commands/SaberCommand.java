package saber.commands;

import saber.tasklist.TaskList;

/**
 * An abstract class to encapsulate a SaberCommand
 */
public abstract class SaberCommand {

    /**
     * Executes the SaberCommand
     *
     * @param taskList the TaskList which will be used by the execute method of various commands
     */
    public abstract void execute(TaskList taskList);

    /**
     * Gets response for the command
     *
     * @param taskList the TaskList which will be used to execute the method of various commands
     * @return response for the command given
     */
    public abstract String getResponse(TaskList taskList);

    /**
     * Determines whether the current command is a terminating command (a ByeCommand)
     *
     * @return boolean isExit
     */
    public boolean isExit() {
        return this instanceof ByeCommand;
    };
}
