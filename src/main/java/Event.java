public class Event extends Task {
    public Event(String taskDetails) {
        super(taskDetails);
    }
    public String toString() {
        return String.format("[E]%s", super.toString());
    }
}
