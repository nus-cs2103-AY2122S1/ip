public class Event extends Task {

    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    public String getTime() {
        return at;
    }

    public String formatSave() {
        return "E | "  + ((super.isDone) ? "1 |" : "0 |") + " " + super.getDescription() + " | " + getTime();
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
