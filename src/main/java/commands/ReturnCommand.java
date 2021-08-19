package commands;
import status.Status;
public class ReturnCommand extends NonExecutableCommand {
    
    public ReturnCommand(String desc) {
        super(desc);
    }

    public ReturnCommand(String desc, String newStatus, String newStoredStatus, boolean flag) {
        super(desc, newStatus, newStoredStatus, flag);
    }

    public ReturnCommand(String desc, String newStatus, String newStoredStatus) {
        super(desc, newStatus, newStoredStatus);
    }

    @Override
    public ReturnCommand updateStatus(String newStatus,  String newStoredStatus) {
        return new ReturnCommand(this.command_description, newStatus, newStoredStatus);
    }

    @Override
    public ReturnCommand isListed() {
        boolean flag = true;
        return new ReturnCommand(this.command_description, this.status, this.storedStatus, flag);
    }

    @Override
    public String toString() {
        if (!this.isListed && this.status.equals(Status.NOT_COMPLETED.getStatus())) {
            return "added: " + this.command_description;
        }
        return this.status + " " + this.command_description;
    }
}
