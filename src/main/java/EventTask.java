public class EventTask extends Task {
    protected String time;

    public EventTask(String description, String time) {
        super(description);
        this.type = "E";
        this.time = time;
    }

    @Override
    public String toString() {
        return super.toString() + " (at: " + this.time + ")";
    }
}
