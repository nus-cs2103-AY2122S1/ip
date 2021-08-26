package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    private String dateTime;
    private final LocalDateTime dt;

    public Deadline(String description) {
        super(description.substring(0, description.indexOf("/by ")).trim());
        this.dateTime = description.substring(description.indexOf("/by ") + 4).trim();
        System.out.println(description);
        this.dt = LocalDateTime.parse(this.dateTime);  //eg LocalDateTime.parse("2015-02-20T06:30");
    }

    @Override
    public String getTaskInfo() {
        return "D" + "|" + super.getZeroOrOne() + "|" + description + "|" + dateTime;
    }

    @Override
    public String toString() {
        String formatDate = dt.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        String formatTime = dt.format(DateTimeFormatter.ofPattern("hh:mm"));
        return "[D]" + super.toString() + "(by: " + formatDate +  ", " + formatTime + ")";
    }
}
