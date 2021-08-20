import java.time.LocalDate;

public class Event extends Task {
    protected LocalDate at;

    public Event(String description, LocalDate at) {
        super(description);
        this.at = at;
    }

    @Override
    public String getTaskType() {
        return "E";
    }

    @Override
    public LocalDate getTime() {
        return this.at;
    }

    @Override
    public String toString() {
        try {
            return "[E]" + super.toString() + " (at: " + Parser.parseLocalDate(this.at) + ")";
        } catch (DukeException dukeException) {
            return dukeException.toString();
        }
    }

}
