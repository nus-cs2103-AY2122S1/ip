package saber.commands;

import saber.tasklist.TaskList;
import saber.ui.ListUI;

/**
 * A class that represents a ListCommand
 */
public class ListCommand extends SaberCommand {
    private ListUI listUI;

    /**
     * A function to execute the ListCommand
     * @param taskList the TaskList to be listed
     */
    public void execute(TaskList taskList) {
        listUI = new ListUI(taskList);
        listUI.showSuccess();
    }

    /**
     * A function to determine whether the current command is a terminating command (a ByeCommand)
     * @return false
     */
    public boolean isExit() {
        return false;
    }
}
