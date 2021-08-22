import java.time.LocalDateTime;

/**
 * CS2103T Individual Project AY 21/22 Sem 1
 * Project Duke
 *
 * Current Progress: Level 8. Dates and Times
 *
 * Description:
 * Extends the Task Class which where it is a task that start at a
 * specific time and ends at a specific time
 *
 * @author Keith Tan
 */
public class Event extends Task {

    private DukeDate duration;

    public Event(String description, DukeDate duration) {
        super(description);
        this.duration = duration;

    }


    @Override
    public String toString() {
        String taskStatus = this.isCompleted() ? "X" : " ";
        return "[" + "E" + "]"
                + "[" + taskStatus + "]"
                + " " + this.getDescription() + " "
                + "(at: " + this.duration.toString() + ")";
    }
}