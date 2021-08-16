public class Deadline extends Task{
    /** A String indicates the latest completion time of the task. **/
    private String byTime;

    /**
     * A public constructor to initialize the Deadline.
     *
     * @param description The description of the task.
     *
     * @param byTime The latest completion time of the task.
     */
    public Deadline(String description, String byTime) {
        super(description);
        this.byTime = byTime;
    }

    /**
     * A public method to generate the type icon of the task.
     *
     * @return A String. The type icon of the task.
     */
    public String getTypeIcon() {
        return "D";
    }

    /**
     * A override toString() method.
     *
     * @return A String. The String representation of the task.
     */
    @Override
    public String toString() {
        return String.format("[%s][%s] %s (by: %s)",
                this.getTypeIcon(), this.getStatusIcon(), this.description, this.byTime);
    }
}
