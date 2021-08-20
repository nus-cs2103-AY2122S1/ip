package commands;
import assignment.*;
import status.Status;

public class EventCommand extends NonExecutableCommand {
    private static final String taskDirectivePoint = "at";
    private static final String assignmentType = AssignmentType.EVENT.getStatus();

    public EventCommand(String desc) {
        super(desc);
    }

    public EventCommand(String desc, String newStatus, String newStoredStatus, boolean flag) {
        super(desc, newStatus, newStoredStatus, flag);
    }

    public EventCommand(String desc, String newStatus, String newStoredStatus) {
        super(desc, newStatus, newStoredStatus);
    }

    @Override
    public EventCommand updateStatus(String newStatus,  String newStoredStatus) {
        return new EventCommand(this.command_description, newStatus, newStoredStatus);
    }

    @Override
    public EventCommand isListed() {
        boolean flag = true;
        return new EventCommand(this.command_description, this.status, this.storedStatus, flag);
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("");
        String[] updatedDesc = this.command_description.split("/");
        sb.append(updatedDesc[0] + "(");
        String[] formattedDesc = updatedDesc[1].split(" ");
        for (int i = 0; i < formattedDesc.length; i++) {
            String c = formattedDesc[i];
            if (c.equals(taskDirectivePoint)) {
                sb.append(c + ": ");
            } else if (i == formattedDesc.length - 1) {
                sb.append(c);
            } else {
                sb.append(c + " ");
            }
        }
        sb.append(")");
        String assignmentProg = assignmentType + this.status + " " + sb.toString();
        if (!this.isListed && this.status.equals(Status.NOT_COMPLETED.getStatus())) {
            String completionMsg = "Got it. I've added this task:";
            return completionMsg + "\n" + assignmentProg;
        }
        return assignmentProg;
    }
}
