import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{

    private LocalDateTime deadline;
    private boolean hasTime;

    public Deadline(String task) {
        super(task.split(" /by ")[0]);
        this.deadline = processDateTime(task.split(" /by ")[1]);
    }

    private LocalDateTime processDateTime(String dateTime) {
        if (dateTime.contains(" ")) {
            this.hasTime = true;
            return LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("d/M/yyyy HH:mm"));
        } else {
            this.hasTime = false;
            return LocalDateTime.parse(dateTime + " 08:00", DateTimeFormatter.ofPattern("d/M/yyyy HH:mm"));
        }
    }

    private String getDateTimeFormatted() {
        return hasTime
                ? this.deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a"))
                : this.deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    @Override
    public String getDescription() {
        return "[D] " + super.getDescription() + " (by: " + this.getDateTimeFormatted() + ")";
    }


    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: " + this.getDateTimeFormatted() + ")";
    }

    @Override
    public int compareTo(Task o) {
        int val = super.compareTo(o);
        if (val == 0) {
            if (o instanceof Deadline) {
                return this.deadline.compareTo(((Deadline) o).deadline);
            } else {
                return this.getDescription().compareTo(o.getDescription());
            }
        } else {
            return val;
        }
    }
}
