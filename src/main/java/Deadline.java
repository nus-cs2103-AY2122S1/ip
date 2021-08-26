import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Deadline extends Task{
    private LocalDate date;

    public Deadline(String task, LocalDate date) {
        super(task, "D");
        this.date = date;
    }

    public String getDate() {
        return this.date.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
    }

    @Override
    public String toString() {
        return String.format("[%s][%s] %s (by: %s)", this.getTaskType(), this.getCompletedMarker(), this.getTask(), this.getDate());
    }
}
