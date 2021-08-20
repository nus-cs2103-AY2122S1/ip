package commands;
import assignment.AssignmentType;
import status.Status;

public class DeadlineCommand extends NonExecutableCommand {
    private static final String taskDirectivePoint = "by";
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
        String outputString = this.getOutputMessage();
        String assignmentProg = assignmentType + this.status + " " + outputString;
        if (!this.isListed && this.status.equals(Status.NOT_COMPLETED.getStatus())) {
            String completionMsg = "Got it. I've added this task:";
            return completionMsg + "\n" + assignmentProg;
        }
        return assignmentProg;
    }


    private String getOutputMessage() {
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
        return sb.toString();
    }
}
