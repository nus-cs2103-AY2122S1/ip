package duke.tasks;
import duke.assignment.AssignmentType;
import duke.date.Date;
import java.util.Optional;
import duke.exceptions.WrongDateFormatException;
import duke.exceptions.WrongTimeFormatException;

/**
 * Class that specifies the properties of deadline task
 */
public class Deadline extends Task {
    private static final String taskDirectivePoint = "by";
    private static final String assignmentType = AssignmentType.DEADLINE.getStatus();
    private static final boolean hasDateTime = true;

    /**
     * Initializes deadline class with a description given by user
     * 
     * @param desc String of the user input
     * @throws WrongDateFormatException if format of date in deadline is wrong
     * @throws WrongTimeFormatException if format of time in deadline is wrong
     */
    public Deadline(String desc) throws WrongDateFormatException, WrongTimeFormatException {
        super(desc, hasDateTime, taskDirectivePoint);
    }

    /**
     * Initializes a new deadline class with a change of the completion status
     * 
     * @param desc String of the user input
     * @param newStatus String updated status of the task
     * @param date Optional date if date exist
     */
    public Deadline(String desc, String newStatus, Optional<Date> date) {
        super(desc, newStatus, date);
    }

    /**
     * returns new deadline task with its completion status updated
     * 
     * @param newStatus String new completion status of the deadline task
     */
    @Override
    public Deadline updateStatus(String newStatus) {
        return new Deadline(this.taskDescription, newStatus, this.date);
    }

    /**
     * returns the string of the deadline task to be stored in storage
     */
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
