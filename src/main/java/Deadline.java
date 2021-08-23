import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.Locale;

public class Deadline extends Task {
    private String name;
    private LocalDateTime deadline;

    Deadline(String name, LocalDateTime deadline) {
        this.name = name;
        this.deadline = deadline;
    }

    @Override
    public String logo() {
        return "[D]";
    }

    @Override
    public String toString() {
        return this.name + " (by: " + this.deadline.getMonth().getDisplayName(TextStyle.SHORT, Locale.ENGLISH) + " " + this.deadline.getDayOfMonth() + " "
                + this.deadline.getYear() + " " + this.deadline.toLocalTime() + ")";
    }

    public String getDeadline() {
        return this.deadline.toString();
    }
}
