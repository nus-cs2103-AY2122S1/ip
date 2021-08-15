/**
 * CS2103T Individual Project AY 21/22 Sem 1
 * Project Duke
 *
 * Current Progress: A-TextUiTesting
 *
 * Description:
 * Extends the Task Class which where it is a task that needs
 * to be done before a specific time
 *
 * @author Keith Tan
 */
public class Deadline extends Task {

    private String dueBy;

    public Deadline(String description, String dueBy) {
        super(description);
        this.dueBy = dueBy;
    }

    /**
     * Getter that returns the deadline of task
     *
     */
    public String getDeadline() {

        return this.dueBy;

    }

    @Override
    public String toString() {
        String taskStatus = this.isCompleted() ? "X" : " ";
        return "[" + "D" + "]"
                + "[" + taskStatus + "]"
                + " " + this.getDescription() + " "
                + "(by: " + this.dueBy + ")";
    }

}
