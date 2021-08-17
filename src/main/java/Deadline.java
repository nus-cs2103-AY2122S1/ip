public class Deadline extends Task {
    private String finishByDate;
    /**
     * Constructor for the Deadline task.
     * @param name Name of the Task.
     * @param finishByDate Date to finish the Task by.
     */
    public Deadline(String name, String finishByDate) {
        super(name);
        this.finishByDate = finishByDate;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + finishByDate + ")";
    }
}