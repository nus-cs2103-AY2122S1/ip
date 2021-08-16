public class Event extends Task {
    protected String date;

    public Event(String description, String date) {
        super(description);
        this.date = date;
    }

    public String getType() {
        return "E";
    }

    public String getDescription() {
        return String.format("%s (by: %s)", this.description, this.date);
    }
}
