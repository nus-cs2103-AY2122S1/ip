package Du;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Event class which is a Task
 */
public class Event extends Task {
    private LocalDateTime time;

    /**
     * Public constructor for Event
     * @param name description for Event
     * @param done whether the Event is done
     * @param tasklist the Tasklist the Event is under
     * @param time time which the Event is at
     */
    public Event(String name, boolean done, TaskList tasklist, LocalDateTime time) {
        super(name, done, tasklist);
        this.time = time;
    }


    /**
     * Records the Event in a certain format to save to the file
     * @return String which the Event is formatted in
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
        return "E , " + state + " , " + name + " , " + time.format(formatter);
    }


    /**
     * toString method
     * @return String for printing
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd yyyy, HHmm");
        return "[E][" + this.getStatus() + "] " + this.name
                + "(at: " + time.getMonth() + " " + time.format(formatter) + ")";
    }
}
