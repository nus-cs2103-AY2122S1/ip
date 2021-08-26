import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private final LocalDate date;
    private final LocalTime time;

    public Deadline(String taskName, LocalDate date, LocalTime time) {
        super(taskName);
        this.date = date;
        this.time = time;
    }

    public Deadline(String taskName, boolean isDone,
                    LocalDate date, LocalTime time) {
        super(taskName, isDone);
        this.date = date;
        this.time = time;
    }

    @Override
    public Task markAsDone() {
        return new Deadline(this.taskName, true, this.date, this.time);
    }

    @Override
    public boolean hasSameDate(LocalDate date) {
        return this.date.equals(date);
    }

    @Override
    public boolean isBeforeDate(LocalDateTime dateTime) {
        // Will also return true if the date times are equal
        return !this.date.atTime(this.time).isAfter(dateTime);
    }

    @Override
    public String toSaveData() {
        return "D|" + super.toSaveData() + "|" + this.date + "|" + this.time;
    }

    @Override
    public String toString() {
        String formattedDate = this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        String formattedTime = this.time.format(DateTimeFormatter.ofPattern("hh:mm a"));
        return "[D]" + super.toString() + " (by: " + formattedDate + ", " + formattedTime + ")";
    }
}
