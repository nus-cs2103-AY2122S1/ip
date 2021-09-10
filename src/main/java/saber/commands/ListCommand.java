package saber.commands;

import saber.tasklist.TaskList;
import saber.ui.ListUI;

/**
 * Represents a ListCommand
 */
public class ListCommand extends SaberCommand {
    private ListUI listUI;

    /**
     * Executes the ListCommand
     *
     * @param taskList the TaskList to be listed
     */
    public void execute(TaskList taskList) {
        listUI = new ListUI(taskList);
        listUI.showSuccess();
    }

    /**
     * {@inheritdoc}
     */
    public String getResponse(TaskList taskList) {
        listUI = new ListUI(taskList);
        return listUI.getSuccessMessage();
    }
}
