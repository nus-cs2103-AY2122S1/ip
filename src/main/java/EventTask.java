public class EventTask extends Task {
    private final String timeDate;

    public EventTask(String taskName, String timeDate) {
        super(taskName);
        this.timeDate = timeDate;
    }

    @Override
    public String toString() {
        return "[E] " + super.toString() + " (at: " + timeDate + ")";
    }
}
