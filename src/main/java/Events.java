public class Events extends Task {

    protected String eventTime;

    public Events(String description, String time) {
        super(description);
        eventTime = time;
    }

    @Override
    public String toString() {
        return String.format("[E] [%s] " + this.description + "(at: %s)",
            this.getStatusIcon(), this.eventTime);
    }

    @Override
    public String toDataFileString() {
        return String.format("E|%s|%s|%s", this.isDone ? "1" : "0", this.description, this.eventTime);
    }
}
