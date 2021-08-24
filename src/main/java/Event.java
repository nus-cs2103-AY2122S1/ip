import java.time.LocalDate;

public class Event extends Task{

    private LocalDate at;

    public Event(String value, LocalDate at) {
        super(value);
        this.at = at;

    }

    @Override
    public LocalDate getTime() {
        return at;
    }

    @Override
    public String getType() {
        return "EVENT";
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
