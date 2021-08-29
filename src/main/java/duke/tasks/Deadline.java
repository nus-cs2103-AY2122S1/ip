package duke.tasks;
import duke.assignment.AssignmentType;
import duke.date.Date;
import java.util.Optional;
import duke.exceptions.WrongDateFormatException;
import duke.exceptions.WrongTimeFormatException;

public class Deadline extends Task {
    private static final String taskDirectivePoint = "by";
    private static final String assignmentType = AssignmentType.DEADLINE.getStatus();
    private static final boolean hasDateTime = true;

    public Deadline(String desc) throws WrongDateFormatException, WrongTimeFormatException {
        super(desc, hasDateTime, taskDirectivePoint);
    }

    public Deadline(String desc, String newStatus, Optional<Date> date) {
        super(desc, newStatus, date);
    }

    @Override
    public Deadline updateStatus(String newStatus) {
        return new Deadline(this.taskDescription, newStatus, this.date);
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
