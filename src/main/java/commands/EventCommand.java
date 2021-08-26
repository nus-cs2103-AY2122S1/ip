package commands;
import assignment.AssignmentType;
import date.Date;
import java.util.Optional;

public class EventCommand extends NonExecutableCommand {
    private static final String taskDirectivePoint = "at";
    private static final String assignmentType = AssignmentType.EVENT.getStatus();
    private static final boolean hasDateTime = true;

    public EventCommand(String desc) {
        super(desc, hasDateTime, taskDirectivePoint);
    }

    public EventCommand(String desc, String newStatus, String newStoredStatus, Optional<Date> date) {
        super(desc, newStatus, newStoredStatus, date);
    }

    @Override
    public EventCommand updateStatus(String newStatus,  String newStoredStatus) {
        return new EventCommand(this.command_description, newStatus, newStoredStatus, this.date);
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
