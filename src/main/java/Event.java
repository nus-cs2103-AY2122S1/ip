/**
 * A class that represent an event which is a type of task that starts
 * and ends at a specific dead or time.
 */
public class Event extends Task {

    /** The time of the event */
    private final DukeDate eventTime;

    private Event(String taskName, DukeDate eventTime) {
        super(taskName, Type.EVENT);
        this.eventTime = eventTime;
    }

    /**
     * Creates a new event task based on the input by a user.
     *
     * @param input An input in the form of a event name followed by the keyword "-at"
     *              then followed by the timing of the event.
     * @return The newly created event task.
     * @throws DukeInvalidDateException Throws an exception when the user tries to create an Event
     * task with a specified date but formats the date wrongly.
     */
    public static Event newEventTask(String input) throws DukeInvalidDateException {
        String[] inputArr = input.split("-at|/at");
        String name = inputArr[0].trim();
        String time = inputArr[1].trim();
        if (input.contains("-at")) {
            return new Event(name, DukeDate.of(time, false));
        } else {
            return new Event(name, DukeDate.of(time, true));
        }
    }

    @Override
    public String taskDescription() {
        return this.getTaskName() + " (at: " + this.eventTime + ")";
    }
}
