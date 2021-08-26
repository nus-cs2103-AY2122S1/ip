package saber.commands;

import saber.TaskList;

/**
 * An abstract class to encapsulate a SaberCommand
 */
public abstract class SaberCommand {

    /**
     * A function to execute the SaberCommand
     * @param taskList the TaskList which will be used by the execute method of various commands
     */
    public abstract void execute(TaskList taskList);

    /**
     * A function to determine whether the current command is a terminating command (a ByeCommand)
     * @return boolean isExit
     */
    public abstract boolean isExit();
}
