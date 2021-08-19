import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class TaskEvent extends Task {
    private LocalDate at;
    private String time;

    public TaskEvent(String description, LocalDate date, String time) {
        super(description);
        System.out.println(Ui.OUTPUT_DISPLAY + "Got it. I've added an Event. Don't miss it!");
        this.at = date;
        this.time = Optional.ofNullable(time)
                .map(String::strip)
                .orElse("");
    }

    /**
     * String representation of Event
     *
     * @return event display
     */
    @Override
    public String toString() {
        String checkBox = done
                ? "[X] "
                : "[ ] ";
        return "[E]" + checkBox + description + " (at: " + at + (!time.equals("") ? " " + time : "") + ")";
    }

}
