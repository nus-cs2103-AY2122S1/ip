package saber.commands;

import saber.tasklist.TaskList;
import saber.ui.FindUI;

/**
 * A class that represents a FindCommand
 */
public class FindCommand extends SaberCommand {
    private String findString;
    private boolean isMissingFindString;

    private FindUI findUI = new FindUI();

    /**
     * A constructor for FindCommand
     * @param findString the keyword
     * @param isMissingFindString whether the keyword is missing
     */
    public FindCommand(String findString, boolean isMissingFindString) {
        this.findString = findString;
        this.isMissingFindString = isMissingFindString;
    }

    /**
     * A function to execute the FindCommand
     * @param taskList the TaskList from where we search for tasks containing the keyword
     */
    public void execute(TaskList taskList) {
        if (isMissingFindString) {
            findUI.showMissingFindStringError();
            return;
        }
        findUI.setSuccessMessage(findString, taskList);
        findUI.showSuccess();
    }

    /**
     * A function to determine whether the current command is a terminating command (a ByeCommand)
     * @return false
     */
    public boolean isExit() {
        return false;
    }
}
