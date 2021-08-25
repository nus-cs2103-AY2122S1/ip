package saber.ui;

import saber.task.Task;

public class AddUI extends SaberCommandUI {
    protected String successMessage;

    protected final String missingDescriptionError = "      I'm sorry, Master.\n"
            + "      What ... exactly do you want me to add?\n";

    public AddUI () {
        this.successMessage = "";
    }

    public void setSuccessMessage(Task task){
        this.successMessage = "      I have added: "  + task + "\n";
    }

    public void showSuccess() {
        System.out.println(successMessage);
    }

    public void showMissingDescriptionError() {
        System.out.println(missingDescriptionError);
    }
}
