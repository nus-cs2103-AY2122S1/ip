public class Event extends Task{
    /** A String indicates the time when the event happens. **/
    private String atTime;

    /**
     * A public constructor to initialize the Deadline.
     *
     * @param description The description of the task.
     *
     * @param atTime The latest completion time of the task.
     */
    public Event(String description, String atTime) {
        super(description);
        this.atTime = atTime;
    }

    /**
     * A public method to generate the type icon of the task.
     *
     * @return A String. The type icon of the task.
     */
    public String getTypeIcon() {
        return "E";
    }

    /**
     * A override toString() method.
     *
     * @return A String. The String representation of the task.
     */
    @Override
    public String toString() {
        return String.format("[%s][%s] %s (at: %s)",
                this.getTypeIcon(), this.getStatusIcon(), this.description, this.atTime);
    }
}
