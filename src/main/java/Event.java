public class Event extends Task {
    private String dateTime;

    Event(String name, String dateTime) {
        super(name);
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + dateTime + ")";
    }
}
