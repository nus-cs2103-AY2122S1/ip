import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class Deadline extends Task {

    private final LocalDateTime dateTime;

    Deadline(String taskName, LocalDateTime dateTime) {
        super(taskName);
        this.dateTime = dateTime;
    }

    private Deadline(Deadline oldDeadline) {
        super(oldDeadline);
        this.dateTime = oldDeadline.dateTime;
    }

    @Override
    Deadline markAsCompleted() {
        return new Deadline(this);
    }

    @Override
    public String toString() {
        return "D: " + super.toString() + " before: " + this.dateTime.format(DateTimeFormatter.ofPattern("E, dd MMM " +
                "yyyy, HH:mm"));
    }
}
