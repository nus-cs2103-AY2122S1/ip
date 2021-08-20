import java.time.LocalDate;

public class Deadline extends Task {
    protected LocalDate by;

    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    @Override
    public String getTaskType() {
        return "D";
    }

    public LocalDate getTime() {
        return this.by;
    }

    @Override
    public String toString() {
        try {
            return "[D]" + super.toString() + " (by: " + Parser.parseLocalDate(this.by) + ")";
        } catch (DukeException dukeException) {
            return dukeException.toString();
        }
    }
}
