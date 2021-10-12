package Du;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDateTime time;

    /**
     * Public constructor for Deadline
     * @param name description for Deadline
     * @param done whether the Deadline is done
     * @param tasklist the Tasklist the Deadline is under
     * @param time time which the Deadline must be done by
     */
    public Deadline(String name, boolean done, TaskList tasklist, LocalDateTime time) {
        super(name, done, tasklist);
        this.time = time;
    }

    /**
     * Records the Deadline in a certain format to save to the file
     * @return String which the Deadline is formatted in
     */
    @Override
    public String log_record() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        int state;
        if (this.isDone) {
            state = 1;
        } else {
            state = 0;
        }
        return "D , " + state + " , " + this.name + " , " + this.time.format(formatter) ;
    }


    /**
     * @return String for printing
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd yyyy, HHmm");
        return "[D][" + this.getStatus() + "] " + this.name
                + "(by: " + time.getMonth() + " " + time.format(formatter) + ")";
    }
}
