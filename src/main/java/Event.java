public class Event extends Task{
    private String date;
    public Event(String event, String date) {
        super(event, "E", date);
        this.date = date;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s (at: %s)", this.getTaskSymbol(), super.toString(), this.date);
    }
}
