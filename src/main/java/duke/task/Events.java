package duke.task;

import duke.Parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Events extends Task {
    protected String at;
    protected LocalDateTime localDateTime;
    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");

    public Events(String description, String at) {
        super(description);
        this.at = at;
    }

    public String getAt() {
        return at;
    }

    @Override
    public String toString() {
        localDateTime = new Parser().parseLocalDateTime(at);
        return "[E]" + super.toString() + " (at: " + localDateTime.format(dateTimeFormatter) + ")";
    }

    @Override
    public void displayTask() {
        System.out.println("        " + toString());

    }
}
