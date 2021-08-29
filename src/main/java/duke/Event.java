package duke;

public class Event extends Task {

    protected String date;

    public Event(String description, String date) {
        super(description);
        this.date = date;
    }

    @Override
    public String convertToFile() {
        return super.description + " | " + this.date;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.date + ")";
    }
}
