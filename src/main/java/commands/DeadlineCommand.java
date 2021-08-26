package commands;
import assignment.AssignmentType;
import date.Date;
import  java.util.Optional;

public class DeadlineCommand extends NonExecutableCommand {
    private static final String taskDirectivePoint = "by";
    private static final String assignmentType = AssignmentType.DEADLINE.getStatus();
    private static final boolean hasDateTime = true;

    public DeadlineCommand(String desc) {
        super(desc, hasDateTime, taskDirectivePoint);
    }

    public DeadlineCommand(String desc, String newStatus, String newStoredStatus, Optional<Date> date) {
        super(desc, newStatus, newStoredStatus, date);
    }

    @Override
    public DeadlineCommand updateStatus(String newStatus,  String newStoredStatus) {
        return new DeadlineCommand(this.command_description, newStatus, newStoredStatus, this.date);
    }

    @Override
    public String getOriginalFormatForStorage() {
        return assignmentType + this.status + 
        " " + this.getOutputMessage(this.date.get().getOriginalFormat());
    }

    @Override
    public String toString() {
        String outputString = this.getOutputMessage(this.date.get().toString());
        String assignmentProg = assignmentType + this.status + " " + outputString;
        return assignmentProg;
    }

    private String getOutputMessage(String dateExpression) {
        StringBuilder sb = new StringBuilder("");
        String[] updatedDesc = this.command_description.split(" ");
        for (int i = 0; i < updatedDesc.length; i++) {
            String item = updatedDesc[i];
            if (item.contains("/" + taskDirectivePoint)) {
                break;
            }
            sb.append(item + " ");
        }
        return sb.toString() + "(" + taskDirectivePoint +
                ": " + dateExpression + ")";
    }
}
