public class Event extends Task {

    private String startTime;

    public Event(String description, String date) {
        super(description);
        this.startTime = date;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), startTime);
    }

    @Override
    public String getType() {
        return "E";
    }

    @Override
    public String getDeadline() {
        return this.startTime;
    }
}
