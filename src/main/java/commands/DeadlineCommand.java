package commands;
import assignment.*;
import status.Status;

public class DeadlineCommand extends NonExecutableCommand {
    private static final String assignmentType = AssignmentType.DEADLINE.getStatus();

    public DeadlineCommand(String desc) {
        super(desc);
    }

    public DeadlineCommand(String desc, String newStatus, String newStoredStatus, boolean flag) {
        super(desc, newStatus, newStoredStatus, flag);
    }

    public DeadlineCommand(String desc, String newStatus, String newStoredStatus) {
        super(desc, newStatus, newStoredStatus);
    }

    @Override
    public DeadlineCommand updateStatus(String newStatus,  String newStoredStatus) {
        return new DeadlineCommand(this.command_description, newStatus, newStoredStatus);
    }

    @Override
    public DeadlineCommand isListed() {
        boolean flag = true;
        return new DeadlineCommand(this.command_description, this.status, this.storedStatus, flag);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("");
        String[] updatedDesc = this.command_description.split("/");
        sb.append(updatedDesc[0] + "(");
        for (Character c : updatedDesc[1].toCharArray()) {
            if (c.equals(' ')) {
                sb.append(": ");
                continue;
            }
            sb.append(String.valueOf(c));
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
