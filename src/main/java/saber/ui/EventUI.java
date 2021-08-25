package saber.ui;

import saber.task.Task;

public class EventUI extends SaberCommandUI {
    protected final String missingDescriptionError = "      I'm sorry, Master.\n"
            + "      I'm ... not sure what event you want me\n"
            + "      to add ...\n";

    protected final String missingTimeError = "      I'm really sorry, Master.\n"
            + "      I won't be able to remind you\n"
            + "      to come to this event without\n"
            + "      knowing the time of the event...\n";

    protected final String parsingTimeError = "      I'm sorry, Master. I'm unable\n"
            + "      to understand the date you\n"
            + "      just told me. Maybe..., Master\n"
            + "      can try to use other format?\n";

    protected String successMessage;

    public EventUI() {
        this.successMessage = "";
    }

    public void setSuccessMessage (Task event, int totalTask) {
        String taskPlural = totalTask <= 1 ? " task" : " tasks";
        this.successMessage = "      Right, Master.\n"
                + "      I'll make sure you remember\n"
                + "      to come to this event:\n"
                + "\n        " + event + "\n"
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
