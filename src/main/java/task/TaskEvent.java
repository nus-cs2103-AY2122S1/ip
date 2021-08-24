package task;

import java.time.LocalDate;
import java.util.Optional;

public class TaskEvent extends Task {
    private final LocalDate at;
    private final String time;

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
        String checkBox = isDone
                ? "[X] "
                : "[ ] ";
        return "[E]" + checkBox + description
                + " (at: " + at + (!time.equals("") ? " "
                + time : "") + ")";
    }

    @Override
    public String saveString() {
        return "E" + '\t'
                + (this.isDone ? "1" : "0") + '\t'
                + this.description + '\t'
                + this.at + '\t'
                + this.time;
    }

    @Override
    public boolean isDate(LocalDate date) {
        return date.equals(at);
    }


}
