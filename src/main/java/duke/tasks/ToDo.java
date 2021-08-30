package duke.tasks;
import duke.assignment.AssignmentType;
import duke.exceptions.WrongDateFormatException;
import duke.exceptions.WrongTimeFormatException;
import duke.date.Date;
import java.util.Optional;

public class ToDo extends Task {
    private static final String assignmentType = AssignmentType.TODO.getStatus();
    private static final boolean hasDateTime = false;

    public ToDo(
            String desc) throws WrongDateFormatException,
            WrongTimeFormatException {
        super(desc, hasDateTime, "");
    }

    public ToDo(String desc, String newStatus, Optional<Date> date) {
        super(desc, newStatus, date);
    }

    @Override
    public String getOriginalFormatForStorage() {
        return this.toString();
    }

    @Override
    public ToDo updateStatus(String newStatus) {
        return new ToDo(this.taskDescription, newStatus, this.date);
    }


    @Override
    public String toString() {
        String assignmentProg = assignmentType +
                this.status + " " + this.taskDescription;
        return assignmentProg;
    }

}
