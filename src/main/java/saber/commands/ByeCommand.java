package saber.commands;

import saber.tasklist.TaskList;
import saber.ui.ByeUI;

/**
 * A class to encapsulate a ByeCommand
 */
public class ByeCommand extends SaberCommand {
    private ByeUI byeUI = new ByeUI();

    /**
     * A function to execute the ByeCommand (in this case, print out the goodbye message)
     * @param taskList the TaskList where tasks are stored
     */
    public void execute(TaskList taskList) {
        byeUI.showSuccess();
    }

    /**
     * {@inheritdoc}
     */
    public String getResponse(TaskList taskList) {
        return byeUI.getSuccessMessage();
    }
}
