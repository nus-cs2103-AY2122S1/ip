import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDateTime time;

    public Event(String name, boolean done, LocalDateTime time) {
        super(name, done);
        this.time = time;
    }


    @Override
    public String log_record() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd yyyy, HHmm");
        int state;
        if (this.done) {
            state = 1;
        } else {
            state = 0;
        }
        return "E , " + state + " , " + name + " , " + time.format(formatter);
    }


    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd yyyy, HHmm");
        return "[E][" + this.getStatus() + "] " + this.name
                + "(at: " + time.getMonth() + " " + time.format(formatter) + ")";
    }
}
