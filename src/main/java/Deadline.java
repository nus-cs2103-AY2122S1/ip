import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private String Dtime;
    private LocalDateTime localDtime;

    public Deadline(String description, String s) {
        super(description);
        this.Dtime = s; // accept format yyyy-mm-dd
        this.localDtime = LocalDateTime.parse(s.substring(1), DateTimeFormatter.ofPattern("uuuu-MM-dd kkmm"));

    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd kkmm");
        String formatDateTime = this.localDtime.format(formatter);
        return "[D]" + super.toString() + " (by:" + formatDateTime + ")";
    }

    @Override
    public String toStringConvert(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd kkmm");
        String formatDateTime = this.localDtime.format(formatter);
        if(this.isCompleted()) {
            return "D | 1 | " +  this.getString() + "| " + formatDateTime;
        } else {
            return "D | 0 | " +  this.getString() + "| " + formatDateTime;
        }
    }

}
