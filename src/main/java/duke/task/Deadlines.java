package duke.task;

import duke.Parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadlines extends Task {
    protected String by;
    protected LocalDateTime localDateTime;
    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");

    public Deadlines(String description, String by) {
        super(description);
        this.by = by;
    }

    public String getBy() {
        return by;
    }

    @Override
    public String toString() {
        localDateTime = new Parser().parseLocalDateTime(by, localDateTime);
        return "[D]" + super.toString() + "(by: " + localDateTime.format(dtf) + ")";
    }

    @Override
    public void displayTask(){
        System.out.println("        " + toString());
    }

}

