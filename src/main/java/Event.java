import java.time.LocalDate;

public class Event extends Task {
    protected LocalDate at;

    public Event(String description, LocalDate at) {
        super(description, "E");
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

    @Override
    public String markDone() {
        this.isDone = true;
        return String.format("Nice! I've marked this task as done:\n  [E][X] %s", this.description);
    }
}
