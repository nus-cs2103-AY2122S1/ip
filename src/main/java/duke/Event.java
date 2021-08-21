package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE, d MMM yyyy hh:mma");
    LocalDateTime time;

    public Event(String description, LocalDateTime time) {
        this.description = description;
        this.time = time;
    }

    @Override
    public String toString() {
        return String.format("[E][%c] %s (at: %s)", isDone ? 'X' : ' ', description, time.format(formatter));
    }
}
