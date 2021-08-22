public class Event extends Task {
    String time;

    public Event(String taskName, String time) {
        super(taskName);
        this.time = time;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), time);
    }

    public String getIdentifier() {
        return "E";
    }

    public String getDetailsWithDelimiter(String delimiter) {
        return String.format("%s%s%s", taskName, delimiter, time);
    }
}