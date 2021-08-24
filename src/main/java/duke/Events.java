package duke;

/**
 * A subclass of Task that represents an Event.
 *
 */
public class Events extends Task {
    String type;
    String time;

    /**
     * Constructor for Deadline class.
     *
     * @param title Name of the task to be created.
     * @param time Date of event.
     *
     */
    public Events(String title, String time) {
        super(title);
        this.type = "E";
        this.time = time;
    }

    @Override
    public String toString() {
        return "[" + type + "]" + super.toString()
                + "(at:" + time + ")";
    }

    /**
     * A method that prints out details of an event.
     *
     * @return Details of an event.
     */
    @Override
    public String writeTask() {
        return type + " | " + super.writeTask()
                + " | " + time;
    }
}