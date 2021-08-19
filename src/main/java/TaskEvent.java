import java.time.LocalDate;
import java.util.Optional;

public class TaskEvent extends Task {
    private LocalDate at;
    private String time;

    public TaskEvent(String description, LocalDate date, String time) {
        this(description, date, time,false);
    }

    public TaskEvent(String description, LocalDate date, String time, boolean done) {
        super(description, done);
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

    @Override
    String saveString() {
        return "E" + '\t'
                + (this.done ? "1" : "0") + '\t'
                + this.description + '\t'
                + this.at + '\t'
                + this.time;
    }


}
