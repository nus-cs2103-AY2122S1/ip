package saber.ui;

import saber.task.Task;
import saber.tasklist.TaskList;

/**
 * Encapsulates the UI corresponding to SortCommand
 */
public class SortUI {
    private final String successMessage;

    /**
     * Constructs SortUI, this constructor will take in the TaskList to be listed
     * and will set the success message according to the TaskList given
     *
     * @param taskList the TaskList containing the tasks to be listed
     */
    public SortUI(TaskList taskList) {
        int totalTask = taskList.size();
        StringBuilder successTemp = new StringBuilder("      Alright, I'll list them in done-ness\n"
                + "      order, Master.\n");

        for (int i = 0; i < totalTask; i++) {
            Task task = taskList.get(i);
            successTemp.append("\n      ").append(i + 1).append(". ").append(task).append("\n");
        }

        if (totalTask == 0) {
            successTemp.append("\n      Ah, currently Master has no task.\n");
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
     * Prints out the success message
     */
    public void showSuccess() {
        System.out.println(successMessage);
    }
}
