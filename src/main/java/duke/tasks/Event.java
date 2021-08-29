package duke.tasks;
import duke.assignment.AssignmentType;
import duke.exceptions.WrongDateFormatException;
import duke.exceptions.WrongTimeFormatException;
import duke.date.Date;
import java.util.Optional;

public class Event extends Task {
    private static final String taskDirectivePoint = "at";
    private static final String assignmentType = AssignmentType.EVENT.getStatus();
    private static final boolean hasDateTime = true;

    public Event(String desc) throws WrongDateFormatException, WrongTimeFormatException {
        super(desc, hasDateTime, taskDirectivePoint);
    }

    public Event(String desc, String newStatus, Optional<Date> date) {
        super(desc, newStatus, date);
    }

    @Override
    public Event updateStatus(String newStatus) {
        return new Event(this.taskDescription, newStatus, this.date);
    }

    @Override
    public String getOriginalFormatForStorage() {
        return assignmentType + this.status + " " + this.getOutputMessage(this.date.get().getOriginalFormat());
    }

    @Override
    public String toString() {
        String outputString = this.getOutputMessage(this.date.get().toString());
        String assignmentProg = assignmentType + this.status + " " + outputString;
        return assignmentProg;
    }

    private String getOutputMessage(String dateExpression) {
        StringBuilder sb = new StringBuilder("");
        String[] updatedDesc = this.taskDescription.split(" ");
        for (int i = 0; i < updatedDesc.length; i++) {
            String item = updatedDesc[i];
            if (item.contains("/" + taskDirectivePoint)) {
                break;
            }
            sb.append(item + " ");
        }
        return sb.toString() + "(" + taskDirectivePoint + ": " + dateExpression + ")";
    }
}
