package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class Deadline extends Task {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE, d MMM yyyy hh:mma");
    private LocalDateTime time;

    Deadline(String description, LocalDateTime time) {
        this.description = description;
        this.time = time;
    }

    @Override
    public String toString() {
        return String.format("[D][%c] %s (by: %s)", isDone ? 'X' : ' ', description, time.format(formatter));
    }
}
