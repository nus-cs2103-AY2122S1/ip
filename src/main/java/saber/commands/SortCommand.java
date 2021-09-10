package saber.commands;

import saber.tasklist.TaskList;
import saber.ui.SortUI;

/**
 * Represents a SortCommand
 */
public class SortCommand extends SaberCommand {
    private SortUI sortUI;

    /**
     * Executes the SortCommand
     *
     * @param taskList the TaskList to be sorted and then listed
     */
    public void execute(TaskList taskList) {
        TaskList temp = taskList.sort();
        sortUI = new SortUI(temp);
        sortUI.showSuccess();
    }

    /**
     * {@inheritdoc}
     */
    public String getResponse(TaskList taskList) {
        TaskList temp = taskList.sort();
        sortUI = new SortUI(temp);
        return sortUI.getSuccessMessage();
    }


}
