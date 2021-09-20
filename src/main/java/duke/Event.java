package duke;

public class Event extends Task{
    private final DateString time;

    public Event(String description) {
        super(description);
        if (description.isBlank()) {
            throw new IllegalArgumentException("Description of Event cannot be empty!");
        }
        this.type = 'E';
        this.time = new DateString("");
    }

    public Event(String description, String time) {
        super(description);
        this.type = 'E';
        this.time = new DateString(time);
    }

    @Override
    public String toString() {

        return super.toString() + " (at: " + this.time.toString() + ")";
    }

    @Override
    public TaskType getType() {
        return TaskType.EVENT;
    }

    @Override
    public int compareTo(Task other) {
        return 0;
    }
}
