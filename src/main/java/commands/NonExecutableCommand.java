package commands;
import status.Status;
public abstract class NonExecutableCommand extends Command {
    protected final String status;
    protected final String storedStatus;
    protected final boolean isListed;

    public NonExecutableCommand(String desc) {
        super(desc);
        this.status = Status.NOT_COMPLETED.getStatus();
        this.storedStatus = Status.NOTSTORED.getStatus();
        this.isListed = false;
    }

    public NonExecutableCommand(String desc, String newStatus, String newStoredStatus, boolean flag) {
        super(desc);
        this.status = newStatus;
        this.storedStatus = newStoredStatus;
        this.isListed = flag;
    }

    public NonExecutableCommand(String desc, String newStatus, String newStoredStatus) {
        super(desc);
        this.status = newStatus;
        this.storedStatus = newStoredStatus;
        this.isListed = false;
    }
    
    @Override
    public boolean isExecutable() {
        return false;
    }

    @Override
    public boolean isForStorage() {
        return true;
    }

    public abstract NonExecutableCommand updateStatus(String newStatus,  String newStoredStatus);

    public abstract NonExecutableCommand isListed();

}
