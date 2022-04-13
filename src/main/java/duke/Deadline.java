package duke;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private String dateTime;
    private LocalDateTime localDateTime;

    /**
     * Constructor for Deadline object
     * @param description for the activity
     * @param s, time the activity is due
     */
    public Deadline(String description, String s) {
        super(description);
        this.dateTime = s; // accept "yyyy-mm-dd kkm" format
        this.localDateTime = LocalDateTime.parse(s.substring(1), DateTimeFormatter.ofPattern("uuuu-MM-dd kkmm"));

    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    /**
     * toString method which returns
     * @return description of the Deadline in a specific format
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd kkmm");
        String formatDateTime = this.localDateTime.format(formatter);
        return "[D]" + super.toString() + " (by:" + formatDateTime + ")";
    }

    /**
     * toStringConvert method which converts a deadline into a specific format for a file
     * @return description for the deadline to write in a file
     */
    @Override
    public String toStringConvert(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd kkmm");
        String formatDateTime = this.localDateTime.format(formatter);
        if(this.isCompleted()) {
            return "D | 1 | " +  this.getString() + "| " + formatDateTime;
        } else {
            return "D | 0 | " +  this.getString() + "| " + formatDateTime;
        }
    }

}
