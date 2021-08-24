package duke;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


public class Event extends Task {
    private String schedule;
    private LocalDateTime localDtime;
    private LocalTime hour;

    /**
     * Constructor for Event class
     * @param description for the activity
     * @param s time the activity will take place
     */
    public Event(String description, String s) {
        super(description);
        this.schedule = s; // accept "yyyy-mm-dd kkmm" format
        this.localDtime = LocalDateTime.parse(s.substring(1), DateTimeFormatter.ofPattern("uuuu-MM-dd kkmm"));
    }

    /**
     * toString method which returns a string representation of an event to be printed
     * @return description of the event in a specific format
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd kkmm");
        String formatDateTime = this.localDtime.format(formatter);

        return "[E]" + super.toString() + " (at:" + formatDateTime + ")";
    }

    /**
     * toStringConvert method which returns a string representation of an event to be printed in a file
     * @return description of the event in a specific format, to write to a file;
     */
    @Override
    public String toStringConvert(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd kkmm");
        String formatDateTime = this.localDtime.format(formatter);

        if(this.isCompleted()) {
            return "E | 1 | " +  this.getString() + "| " + formatDateTime;
        } else {
            return "E | 0 | " +  this.getString() + "| " + formatDateTime;
        }
    }


}