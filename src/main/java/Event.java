import java.time.LocalDateTime;

/**
 * A subclass of Task of event type.
 */
public class Event extends Task {
    private LocalDateTime datetime;

    Event(String name, LocalDateTime datetime) {
        super(name);
        this.datetime = datetime;
    }

    /**
     * Shows all statuses appended with name.
     *
     * @return A status string containing name.
     */
    @Override
    public String showStatus() {
        String status =  super.showStatus();
        return status + " (by: " + datetime.toString().replace('T', ' ') + ")";
    }

    /**
     * Returns a status string indicating type of task.
     *
     * @return An indicator string for the type of task.
     */
    @Override
    public String printType() {
        return "[E]";
    }

}
