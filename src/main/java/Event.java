public class Event extends DateDependentTask {
    public Event(String description, String at) {
        super(description, at);
    }

    public String getShortForm() {
        return "E";
    }

    @Override
    public String toString() {
        return "[E] " + super.toString() + " (at: " + super.getDate() + ")";
    }
}