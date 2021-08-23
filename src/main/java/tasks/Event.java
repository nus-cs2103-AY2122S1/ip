package tasks;

public class Event extends Task {
    protected String at;

    public Event(String description, String time) {
        super(description);
        this.at = time;
    }

    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.at +")";
    }

    @Override
    public String saveFormat() {
        return String.join(Task.delimiter,
                            super.saveFormat(),
                            this.at);
    }
}
