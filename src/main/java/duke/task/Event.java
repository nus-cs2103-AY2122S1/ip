package duke.task;

import java.time.DateTimeException;
import java.time.LocalDate;

public class Event extends Task{

    private LocalDate time;

    public Event(String description, LocalDate time) {
        super(description);
        this.time = time;
    }

    public static Task of(boolean isDone, String description, String time) throws DateTimeException{
        Task ret = new Event(description, LocalDate.parse(time));
        return isDone ? ret.done() : ret;
    }

    @Override
    public String getTaskType() { return "E"; }

    @Override
    public String toDatabaseString() {
        return String.format("%s|%s", super.toDatabaseString(), this.time);
    }

    @Override
    public String toString() {
        return String.format("[%s]%s (at: %s)", this.getTaskType() , super.toString(), this.time);
    }
}
