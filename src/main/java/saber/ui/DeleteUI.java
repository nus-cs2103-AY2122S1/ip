package saber.ui;

import saber.task.Task;

public class DeleteUI extends SaberCommandUI {
    protected String successMessage;

    protected final String unableToFindTaskError = "      I'm really sorry, Master.\n"
            + "      I'm unable to find the task that\n"
            + "      you want to delete...\n";

    protected final String argumentError = "      I'm really sorry, Master.\n"
            + "      I'm ... not sure which task you want\n"
            + "      to delete...\n";

    public DeleteUI() {
        this.successMessage = "";
    }

    public void setSuccessMessage (Task task, int totalTask) {
        String taskPlural = totalTask <= 1 ? " task" : " tasks";

        this.successMessage = "      Understand, Master.\n"
                + "      I have deleted this task.\n"
                + "\n        " + task + "\n"
                + "\n      Currently, Master has " + totalTask + taskPlural
                + "\n      in the list." + "\n";
    }

    public void showSuccess() {
        System.out.println(successMessage);
    }

    public void showUnableToFindTaskError() {
        System.out.println(unableToFindTaskError);
    }

    public void showArgumentError() {
        System.out.println(argumentError);
    }
}
