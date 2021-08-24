package duke.task;

import java.time.LocalDate;

public class Event extends Task {
    protected LocalDate at;

    public Event(String description, LocalDate at) {
        super(description, "event");
        this.at = at;
    }

    @Override
    public String saveTaskFormat(){
        return "E" + super.saveTaskFormat() + String.format("|%s", at);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
