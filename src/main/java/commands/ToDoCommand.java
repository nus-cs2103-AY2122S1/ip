package commands;
import assignment.AssignmentType;
import date.Date;
import java.util.Optional;

public class ToDoCommand extends NonExecutableCommand {
    private static final String assignmentType = AssignmentType.TODO.getStatus();
    private static final boolean hasDateTime = false;

    public ToDoCommand(String desc) {
        super(desc, hasDateTime, "");
    }

    public ToDoCommand(String desc, String newStatus, String newStoredStatus, Optional<Date> date) {
        super(desc, newStatus, newStoredStatus, date);
    }

    @Override
    public String getOriginalFormatForStorage() {
        return this.toString();
    }

    @Override
    public ToDoCommand updateStatus(String newStatus,  String newStoredStatus) {
        return new ToDoCommand(this.command_description, newStatus, newStoredStatus, this.date);
    }


    @Override
    public String toString() {
        String assignmentProg = assignmentType + this.status + " " + this.command_description;
        return assignmentProg;
    }

}
