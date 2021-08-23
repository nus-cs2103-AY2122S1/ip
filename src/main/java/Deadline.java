import java.time.LocalDate;

public class Deadline extends Task {
    private LocalDate deadline;

    public Deadline(String description, LocalDate deadline) {
        super(description);
        this.deadline = deadline;
    }

    public LocalDate getDeadline() {
        return this.deadline;
    }
}
