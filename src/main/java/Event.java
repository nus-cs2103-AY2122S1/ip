/**
 * CS2103T Individual Project AY 21/22 Sem 1
 * Project Duke
 *
 * Current Progress: Level 4. ToDos, Events, Deadlines
 *
 * Description:
 * Extends the Task Class which where it is a task that start at a
 * specific time and ends at a specific time
 *
 * @author Keith Tan
 */
public class Event extends Task {

    private String duration;

    public Event(String description, String duration) {
        super(description);
        this.duration = duration;

    }

    /**
     * Getter that returns the duration of the event
     *
     */
    public String getDuration() {

        return this.duration;

    }

    @Override
    public String toString() {
        String taskStatus = this.isCompleted() ? "X" : " ";
        return "[" + "E" + "]"
                + "[" + taskStatus + "]"
                + " " + this.getDescription() + " "
                + "(at: " + this.duration + ")";
    }
}
