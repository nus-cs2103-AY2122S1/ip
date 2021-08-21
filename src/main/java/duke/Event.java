package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class Event extends Task {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE, d MMM yyyy hh:mma");
    private LocalDateTime time;

    Event(String description, LocalDateTime time) {
        this.description = description;
        this.time = time;
    }

    @Override
    public String toString() {
        return String.format("[E][%c] %s (at: %s)", isDone ? 'X' : ' ', getDescription(), time.format(formatter));
    }
}
