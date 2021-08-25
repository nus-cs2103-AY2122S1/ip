package saber.ui;

import saber.TaskList;
import saber.task.Task;

public class FindUI extends SaberCommandUI {
    private String successMessage;

    protected final String missingFindStringError = "      I'm really sorry, Master.\n"
            + "      I don't quite know what task you want me\n"
            + "      to find ...\n";

    public FindUI() {
        this.successMessage = "";
    }

    public void setSuccessMessage(String findString, TaskList taskList) {
        int totalTask = taskList.size();
        boolean hasFoundAtLeastOneTask = false;
        StringBuilder successTemp = new StringBuilder("      Right away, Master. Please give me a\n" +
                "      moment to recollect everything ...\n" + "\n" +
                "      I'll list them for you, Master.\n");

        for (int i = 0; i < totalTask; i++) {
            Task task = taskList.get(i);
            String taskDescription = task.getDescription();

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

    public void showSuccess() {
        System.out.println(successMessage);
    }

    public void showMissingFindStringError() {
        System.out.println(missingFindStringError);
    }
}
