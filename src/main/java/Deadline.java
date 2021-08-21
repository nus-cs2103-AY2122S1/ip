import java.time.LocalDate;

public class Deadline extends Task {
    private LocalDate finishByDate;
    /**
     * Constructor for the Deadline task.
     * @param name Name of the Task.
     * @param finishByDate Date to finish the Task by.
     */
    public Deadline(String name, LocalDate finishByDate) {
        super(name);
        this.finishByDate = finishByDate;
    }

    /**
     * Retrieves the date of the deadline of the task.
     * @return The deadline of the task.
     */
    public String getDate() {
        return finishByDate;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + finishByDate + ")";
    }
}