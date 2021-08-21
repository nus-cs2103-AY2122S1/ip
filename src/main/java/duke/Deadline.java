package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE, d MMM yyyy hh:mma");
    LocalDateTime time;

    public Deadline(String description, LocalDateTime time) {
        this.description = description;
        this.time = time;
    }

    @Override
    public String toString() {
        return String.format("[D][%c] %s (by: %s)", isDone ? 'X' : ' ', description, time.format(formatter));
    }
}
