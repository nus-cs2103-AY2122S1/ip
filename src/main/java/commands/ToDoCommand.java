package commands;
import assignment.*;
import status.Status;
public class ToDoCommand extends NonExecutableCommand {
    private static final String assignmentType = AssignmentType.TODO.getStatus();

    public ToDoCommand(String desc) {
        super(desc);
    }

    public ToDoCommand(String desc, String newStatus, String newStoredStatus, boolean flag) {
        super(desc, newStatus, newStoredStatus, flag);
    }

    public ToDoCommand(String desc, String newStatus, String newStoredStatus) {
        super(desc, newStatus, newStoredStatus);
    }

    @Override
    public ToDoCommand updateStatus(String newStatus,  String newStoredStatus) {
        return new ToDoCommand(this.command_description, newStatus, newStoredStatus);
    }

    @Override
    public ToDoCommand isListed() {
        boolean flag = true;
        return new ToDoCommand(this.command_description, this.status, this.storedStatus, flag);
    }

    @Override
    public String toString() {
        String assignmentProg = assignmentType + this.status + " " + this.command_description;
        if (!this.isListed && this.status.equals(Status.NOT_COMPLETED.getStatus())) {
            String completionMsg = "Got it. I've added this task:";
            return completionMsg + "\n" + assignmentProg;
        }
        return assignmentProg;
    }

}
