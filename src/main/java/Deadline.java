/**
 * A task that need to be done before a certain date
 */
public class Deadline extends Task {
    /** Date that the task needs to be done before **/
    private String endDate;

    /**
     * Initializes a new Deadline
     * @param name Name of Deadline
     * @param endDate Date to complete task by
     */
    public Deadline(String name, String endDate) {
        super(name);
        this.endDate = endDate;
    }

    public String getEndDate() {
        return endDate;
    }

    @Override
    public String toString() {
        String isDone = isDone() ? "x" : " ";
        return String.format("[D][%s] %s (by: %s)", isDone, getName(), getEndDate());
    }
}
