package commands;
import status.Status;

public class ReadCommand extends NonExecutableCommand {
    
    public ReadCommand(String desc) {
        super(desc);
    }

    public ReadCommand(String desc, String newStatus, String newStoredStatus, boolean flag) {
        super(desc, newStatus, newStoredStatus, flag);
    }

    public ReadCommand(String desc, String newStatus, String newStoredStatus) {
        super(desc, newStatus, newStoredStatus);
    }

    @Override
    public ReadCommand updateStatus(String newStatus,  String newStoredStatus) {
        return new ReadCommand(this.command_description, newStatus, newStoredStatus);
    }

    @Override
    public ReadCommand isListed() {
        boolean flag = true;
        return new ReadCommand(this.command_description, this.status, this.storedStatus, flag);
    }

    @Override
    public String toString() {
        if (!this.isListed && this.status.equals(Status.NOT_COMPLETED.getStatus())) {
            return "added: " + this.command_description;
        }
        return this.status + " " + this.command_description;
    }
}
