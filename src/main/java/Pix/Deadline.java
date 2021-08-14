package Pix;

public class Deadline extends Task {
    private String finishByDate;
    /**
     * Constructor for the Pix.Deadline task.
     * @param name Name of the Pix.Task.
     * @param finishByDate Date to finish the Pix.Task by.
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
