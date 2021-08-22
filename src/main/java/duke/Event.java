package duke;

import java.time.LocalDate;

public class Event extends Task{
    private LocalDate time;

    public Event(String taskTitle, LocalDate time) {
        super(taskTitle);
        this.time = time;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + time.getMonth().toString() + " "  +
                time.getDayOfMonth() + " " + time.getYear() + ")";
    }
}
