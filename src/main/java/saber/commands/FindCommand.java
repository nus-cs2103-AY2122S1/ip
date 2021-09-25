package saber.commands;

import saber.tasklist.TaskList;
import saber.ui.FindUI;

/**
 * Represents a FindCommand
 */
public class FindCommand extends SaberCommand {
    private String findString;
    private boolean isMissingFindString;

    private FindUI findUI = new FindUI();

    /**
     * Constructs for FindCommand
     *
     * @param findString the keyword
     * @param isMissingFindString whether the keyword is missing
     */
    public FindCommand(String findString, boolean isMissingFindString) {
        this.findString = findString;
        this.isMissingFindString = isMissingFindString;
    }

    /**
     * Executes the FindCommand
     *
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
     * {@inheritdoc}
     */
    public String getResponse(TaskList taskList) {
        if (isMissingFindString) {
            return findUI.getMissingFindStringError();
        }
        findUI.setSuccessMessage(findString, taskList);
        return findUI.getSuccessMessage();
    }
}
