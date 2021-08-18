public class Event extends Task {

    private final String at;

    public Event(String description, String by) {
        super(description, TaskType.EVENT);
        this.at = by;
    }

    @Override
    public String getDescriptionWithStatus() {
        return "[E]" + super.getDescriptionWithStatus() + " (at: " + at + ")";
    }

}
