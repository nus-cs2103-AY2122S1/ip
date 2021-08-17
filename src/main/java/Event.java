/**
 * A subclass of Task of event type.
 */
public class Event extends Task {
    private String timeInfo;

    Event(String name, String timeInfo) {
        super(name);
        this.timeInfo = timeInfo;
    }

    @Override
    public String showStatus() {
        String status =  super.showStatus();
        return status + " (at: " + timeInfo + ")";
    }

    @Override
    public String printType() {
        return "[E]";
    }

}
