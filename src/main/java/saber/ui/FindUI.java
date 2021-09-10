package saber.ui;

import saber.task.Task;
import saber.tasklist.TaskList;

/**
 * Encapsulates the UI corresponding to FindCommand
 */
public class FindUI extends SaberCommandUI {
    protected final String missingFindStringError = "      I'm really sorry, Master.\n"
            + "      I don't quite know what task you want me\n"
            + "      to find ...\n";

    private String successMessage;

    /**
     * Constructs FindUI which will initialize the success message to an empty string
     */
    public FindUI() {
        this.successMessage = "";
    }

    /**
     * Sets the success message for the UI
     *
     * @param findString the keyword to find the related tasks with
     * @param taskList the TaskList from which we need to find all tasks containing the keyword
     */
    public void setSuccessMessage(String findString, TaskList taskList) {
        int totalTask = taskList.size();
        boolean hasFoundAtLeastOneTask = false;
        StringBuilder successTemp = new StringBuilder("      Right away, Master. Please give me a\n"
                + "      moment to recollect everything ...\n" + "\n"
                + "      I'll list them for you, Master.\n");

        for (int i = 0; i < totalTask; i++) {
            Task task = taskList.get(i);
            String taskDescription = task.getDescription();

            // Match lower case text so that the keyword is not case sensitive
            if (taskDescription.toLowerCase().contains(findString.toLowerCase())) {
                hasFoundAtLeastOneTask = true;
                successTemp.append("\n      ").append(i + 1).append(". ").append(task).append("\n");
            }
        }

        if (!hasFoundAtLeastOneTask) {
            successTemp.append("\n      Ah, currently Master has no such task.\n");
        }
        this.successMessage = successTemp.toString();
    }

    /**
     * Gets success message
     *
     * @return success message
     */
    public String getSuccessMessage() {
        return successMessage;
    }

    /**
     * Gets missing find string error
     *
     * @return missing find string error
     */
    public String getMissingFindStringError() {
        return missingFindStringError;
    }

    /**
     * Prints out the success message
     */
    public void showSuccess() {
        System.out.println(successMessage);
    }

    /**
     * Prints out the missing find string error when there is no keyword supplied to the find command
     */
    public void showMissingFindStringError() {
        System.out.println(missingFindStringError);
    }
}
