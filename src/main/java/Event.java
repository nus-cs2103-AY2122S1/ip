public class Event extends Task {
    protected String at;

    public Event(String description, String at, boolean isDone, boolean hasNotif) {
        super(description, isDone);
        this.at = at;
        this.category = Category.EVENT;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
