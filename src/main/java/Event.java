/**
 * A task with a start and end time
 */
public class Event extends Task {
    /** A string representing the start and end time of the event **/
    private String startEndTime;

    /**
     * Initializes a new Event
     * @param name Name of task
     * @param startTime Start time of task
     */
    public Event(String name, String startTime) {
        super(name);
        this.startEndTime = startTime;
    }

    public String getStartEndTime() {
        return startEndTime;
    }

    @Override
    public String toString() {
        String isDone = isDone() ? "x" : " ";
        return String.format("[E][%s] %s (at: %s)", isDone, getName(), getStartEndTime());
    }
}
