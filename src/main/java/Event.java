/**
 * This class implements a DukeList to be used in storing string from Duke
 *
 * @author Amos Tan
 * @version CS2103T AY21/22 Semester 1
 */

public class Event extends Task {
    /** The day of the event.*/
    protected String day;
    /** The time which the event takes place.*/
    protected String time;

    /**
     * Constructs an Event.
     * @param name The name of the event.
     * @param day The day of the event.
     * @param time The time which the event takes place.
     */
    public Event(String name, String day, String time) {
        super(name);
        this.day = day;
        this.time = time;
    }

    /**
     * Returns a simplified representation of the Event for easier recovery from save file.
     * @return The file formatted string representation of the Event.
     */
    public String toFileFormat() {
        return String.format("E%s,%s,%s,%s", name, day, time, isDone);
    }

    /**
     * Returns a string representation of the Evenr, with an [X] marked for done and [ ] as undone.
     * @return the string representation of the Event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + day + " " + time + ")";
    }
}
