public class Event extends Task {

    private String startTime;

    public Event(String description) {
        super(description.split(" /at ")[0]);
        this.startTime = description.split(" /at ")[1];
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), startTime);
    }
}
