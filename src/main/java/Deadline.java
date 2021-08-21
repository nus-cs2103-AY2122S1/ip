import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private final LocalDate deadline;

    Deadline(String body, LocalDate deadline) {
        super(body, false);
        this.deadline = deadline;
    }

    Deadline(String body, boolean done, LocalDate deadline) {
        super(body, done);
        this.deadline = deadline;
    }

    public LocalDate getDeadline() {
        return this.deadline;
    }

    @Override
    Task setDone() {
        return new Deadline(this.getBody(), true, this.deadline);
    }

    @Override
    public String toString() {
        if (this.getDone()) {
            return "[D] [X]" + this.getBody() + " (by: " + this.deadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
        }
        else {
            return "[D] [ ]" + this.getBody() + " (by: " + this.deadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
        }
    }
}
