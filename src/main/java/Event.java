/**
 * A class that represent an event which is a type of task that starts
 * and ends at a specific dead or time.
 */
public class Event extends Task {

    private final String eventTime;

    private Event(String taskName, String eventTime) {
        super(taskName, Type.EVENT);
        this.eventTime = eventTime;
    }

    /**
     * Creates a new event task based on the input by a user.
     * @param input An input in the form of a event name followed by the keyword "-at"
     *              then followed by the timing of the event.
     * @return The newly created event task.
     */
    public static Event newEventTask(String input) {
        String name = input.split(" -at ")[0];
        String time = input.split(" -at ")[1];
        return new Event(name, time);
    }

    @Override
    public String taskDescription() {
        return this.getTaskName() + " (at: " + this.eventTime + ")";
    }
}
