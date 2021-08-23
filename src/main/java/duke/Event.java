package duke;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


public class Event extends Task {
    private String schedule;
    private LocalDateTime localDtime;
    private LocalTime hour;

    public Event(String description, String s) {
        super(description);
        this.schedule = s; // accept "yyyy-mm-dd kkmm" format
        this.localDtime = LocalDateTime.parse(s.substring(1), DateTimeFormatter.ofPattern("uuuu-MM-dd kkmm"));
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd kkmm");
        String formatDateTime = this.localDtime.format(formatter);

        return "[E]" + super.toString() + " (at:" + formatDateTime + ")";
    }


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