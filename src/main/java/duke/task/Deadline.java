package duke.task;

import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.Locale;

public class Deadline extends Task {
    private String name;
    private LocalDateTime deadline;

    public Deadline(String name, LocalDateTime deadline) {
        this.name = name;
        this.deadline = deadline;
    }

    @Override
    public String logo() {
        return "[D]";
    }

    @Override
    public String toString() {
        return this.name + " (by: " + this.deadline.getMonth().getDisplayName(TextStyle.SHORT, Locale.ENGLISH) + " " +
                this.deadline.getDayOfMonth() + " " + this.deadline.getYear() + " " + this.deadline.toLocalTime() + ")";
    }

    public String getDeadline() {
        Integer month = this.deadline.getMonthValue();
        String strMonth = (month.toString().length() == 1) ? "0" + month.toString() : month.toString();
        return  this.deadline.getYear() + "-" + strMonth + "-"
                + this.deadline.getDayOfMonth() + " " + this.deadline.toLocalTime();
    }

    public String getName() {
        return this.name;
    }
}
