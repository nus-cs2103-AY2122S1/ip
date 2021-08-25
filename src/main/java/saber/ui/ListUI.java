package saber.ui;

import saber.task.Task;
import saber.TaskList;

public class ListUI extends SaberCommandUI {
    private final String successMessage;
    protected final String missingDescriptionError = "      I'm sorry, Master.\n"
            + "      What ... exactly do you want me to add?\n";

    public ListUI(TaskList taskList) {
        int totalTask = taskList.size();
        StringBuilder successTemp = new StringBuilder("      Would you like to know what you\n" +
                "      told me to remember?\n" + "\n" +
                "      I'll list them for you, Master.\n");

        for (int i = 0; i < totalTask; i++) {
            Task task = taskList.get(i);
            successTemp.append("\n      ").append(i + 1).append(". ").append(task).append("\n");
        }

        if (totalTask == 0) {
            successTemp.append("\n      Ah, currently Master has no task.\n");
        }

        this.successMessage = successTemp.toString();
    }

    public void showSuccess() {
        System.out.println(successMessage);
    }

    public void showMissingDescriptionError() {
        System.out.println(missingDescriptionError);
    }
}
