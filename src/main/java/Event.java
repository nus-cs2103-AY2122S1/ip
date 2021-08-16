public class Event extends Task{
    private final static String symbol = "[E]";
    private String deadline;

    public Event(String action, String deadline) {
        super(action);
        this.deadline = deadline;
    }

    public String toString() {
        return String.format("%s%s (at: %s)", symbol, super.toString(), deadline);
    }
}
