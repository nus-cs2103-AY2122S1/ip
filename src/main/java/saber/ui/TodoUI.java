package saber.ui;

import saber.task.Task;

public class TodoUI extends SaberCommandUI {
    protected String successMessage;

    protected final String missingDescriptionError = "      I'm sorry, Master.\n"
            + "      What ... exactly do you want me to add\n"
            + "      to your saber.task.Todo list?\n";

    public TodoUI() {
        this.successMessage = "";
    }

    public void setSuccessMessage(Task todo, int totalTask) {
        String taskPlural = totalTask <= 1 ? " task" : " tasks";

        this.successMessage = "      Yes, Master.\n"
                + "      I'll add the following to your saber.task.Todo list:\n"
                + "\n        " + todo + "\n"
                + "\n      Currently, Master has " + totalTask + taskPlural
                + "\n      in the list." + "\n";
    }

    public void showSuccess() {
        System.out.println(successMessage);
    }

    public void showMissingDescriptionError() {
        System.out.println(missingDescriptionError);
    }
}
