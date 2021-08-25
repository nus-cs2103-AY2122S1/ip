package saber.ui;

import saber.task.Task;

public class DeadlineUI extends SaberCommandUI {
    private String successMessage;

    protected final String missingDescriptionError = "      I'm really sorry, Master.\n"
            + "      I'm ... not sure what task you want me\n"
            + "      to add ...\n";

    protected final String missingTimeError = "      I'm sorry, Master.\n"
            + "      I won't be able to ensure that you\n"
            + "      do the task on time without\n"
            + "      knowing the deadline...\n";

    protected final String parsingTimeError = "      I'm sorry, Master. I'm unable\n"
            + "      to understand the date you\n"
            + "      just told me. Maybe..., Master\n"
            + "      can try to use other format?\n";

    public DeadlineUI() {
        this.successMessage = "";
    }

    public void setSuccessMessage(Task deadline, int totalTask) {
        String taskPlural = totalTask <= 1 ? " task" : " tasks";

        this.successMessage = "      Right, Master.\n"
                + "      I'll ensure that you will\n"
                + "      do this task before the deadline:\n"
                + "\n        " + deadline + "\n"
                + "\n      Currently, Master has " + totalTask + taskPlural
                + "\n      in the list." + "\n";
    }

    public void showSuccess() {
        System.out.println(successMessage);
    }

    public void showMissingDescriptionError() {
        System.out.println(missingDescriptionError);
    }

    public void showMissingTimeError() {
        System.out.println(missingTimeError);
    }

    public void showParsingTimeError() {
        System.out.println(parsingTimeError);
    }
}
