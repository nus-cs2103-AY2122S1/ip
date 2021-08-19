import java.time.LocalDate;
import java.util.Optional;

public class TaskDeadline extends Task {
    private LocalDate by;
    private String time;

    public TaskDeadline(String description, LocalDate date, String time) {
        this(description, date, time,false);
    }

    public TaskDeadline(String description, LocalDate date, String time, boolean done) {
        super(description, done);
        this.by = date;
        this.time = Optional.ofNullable(time)
                .map(String::strip)
                .orElse("");
    }
    /**
     * String representation of Deadline
     *
     * @return deadline display
     */
    @Override
    public String toString() {
        String checkBox = done
                ? "[X] "
                : "[ ] ";
        return "[D]" + checkBox + description + " (by: " + by + (!time.equals("") ? " " + time : "") + ")";
    }

    @Override
    String saveString() {
        return "D" + '\t'
                + (this.done ? "1" : "0") + '\t'
                + this.description + '\t'
                + this.by + '\t'
                + this.time;
    }

}
