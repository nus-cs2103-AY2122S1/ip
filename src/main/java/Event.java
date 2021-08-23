/**
 * A subclass of Task of event type.
 */
public class Event extends Task {
    private String timeInfo;

    Event(String name, String timeInfo) {
        super(name);
        this.timeInfo = timeInfo;
    }

    Event(String name, String timeInfo, boolean isDone) {
        super(name, isDone);
        this.timeInfo = timeInfo;
    }

    @Override
    public String showStatus() {
        String status =  super.showStatus();
        return status + " (at: " + timeInfo + ")";
    }

    @Override
    public String showStatusWrite() {
        return this.printType() + this.printCompletionStatus()
                + Separator.SEPARATOR + this.name
                + Separator.SEPARATOR + this.timeInfo;
    }

    @Override
    public String printType() {
        return "[E]";
    }

}
