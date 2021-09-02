package duke.commands;

import duke.TaskList;

/**
 * This is an interface to handle all known Commands from user.
 */
public interface Command {

    /**
     * This abstract method takes in the TaskList Duke is using to handle
     * commands given by the user and make changes to existing tasks.
     *
     * @param taskList TaskList which contains current tasks Duke is using.
     * @return String description for Duke to show user in GUI.
     */
    public String execute(TaskList taskList);
}
