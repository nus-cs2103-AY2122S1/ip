import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class TaskDeadline extends Task {
    private LocalDate by;
    private String time;

    public TaskDeadline(String description, LocalDate date, String time) {
        super(description);
        System.out.println(Ui.OUTPUT_DISPLAY + "Got it. I've added a Deadline.");
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

}
