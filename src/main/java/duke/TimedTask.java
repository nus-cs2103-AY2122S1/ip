package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public abstract class TimedTask extends Task {
    protected LocalDate time;
    public TimedTask(String name, String time) {
        super(name, true);
        this.time = LocalDate.parse(time);
    }

    public TimedTask(String name, String time, boolean isDone) {
        super(name, true, isDone);
        this.time = LocalDate.parse(time);
    }

    public abstract String format(DateTimeFormatter dtformatter);
}
