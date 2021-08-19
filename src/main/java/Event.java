public class Event extends Task{
    private final String time;
    public Event(String description) {
        super(description);
        if (description.isBlank()) {
            throw new IllegalArgumentException("Description of Event cannot be empty!");
        }
        this.type = 'E';
        this.time = "";
    }

    public Event(String description, String time) {
        super(description);
        this.type = 'E';
        this.time = time;
    }

    @Override
    public String toString() {
        return super.toString() + " (at: " + this.time + ")";
    }
}
