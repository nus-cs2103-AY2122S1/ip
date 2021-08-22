package Du;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDateTime time;

    public Deadline(String name, boolean done, TaskList tasklist, LocalDateTime time) {
        super(name, done, tasklist);
        this.time = time;
    }

    @Override
    public String log_record() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        int state;
        if (this.done) {
            state = 1;
        } else {
            state = 0;
        }
        return "D , " + state + " , " + this.name + " , " + this.time.format(formatter) ;
    }


    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd yyyy, HHmm");
        return "[D][" + this.getStatus() + "] " + this.name
                + "(by: " + time.getMonth() + " " + time.format(formatter) + ")";
    }
}
