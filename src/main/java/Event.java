public class Event extends Task {
    private String date;

    public Event(String description, String date, boolean isDone) {
        super(description, isDone);
        this.date = date;
    }

    @Override
    public String toDataString() {
        return String.format("E | %d | %s | %s", isDone ? 1 : 0, this.description, this.date);
    }
    
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + date + ")";
    }
}
