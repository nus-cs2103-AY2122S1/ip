package commands;
import status.Status;
import java.util.Optional;
import date.Date;

public abstract class NonExecutableCommand extends Command {
    protected final String status;
    protected final String storedStatus;
    protected final Optional<Date> date;

    public NonExecutableCommand(String desc, boolean hasDateTime, String taskDirective) {
        super(desc);
        this.status = Status.NOT_COMPLETED.getStatus();
        this.storedStatus = Status.NOTSTORED.getStatus();
        this.date = this.getDateAndTime(desc, hasDateTime, taskDirective);
    }

    private Optional<Date> getDateAndTime(String desc, boolean hasDateTime, String taskDirective) {
        if (hasDateTime) {
            String[] descArray = desc.split("/" + taskDirective);
            String dateAndTime = descArray[1].trim();
            Date outputDate = new Date(dateAndTime);
            return Optional.ofNullable(outputDate);
        }
        return Optional.empty();
    }

    public NonExecutableCommand(String desc, String newStatus, String newStoredStatus, Optional<Date> date) {
        super(desc);
        this.status = newStatus;
        this.storedStatus = newStoredStatus;
        this.date = date;
    }

    public abstract String getOriginalFormatForStorage();
    
    @Override
    public boolean isExecutable() {
        return false;
    }

    @Override
    public boolean isForStorage() {
        return true;
    }

    public abstract NonExecutableCommand updateStatus(String newStatus,  String newStoredStatus);


}
