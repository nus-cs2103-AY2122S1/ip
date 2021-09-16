package duke.task;

/**
 * CS2103T Individual Project AY 21/22 Sem 1
 * Project Duke
 *
 *
 * Description:
 * Extends the Task Class which where it is a task that does
 * not have a date/time attached to it
 *
 * @author Keith Tan
 */
public class ToDo extends Task {

    /**
     * Constructor for ToDo
     * Todo is a task without a duration
     */
    public ToDo(String description) {
        super(description);

    }

    @Override
    public String toString() {
        String taskStatus = this.isCompleted() ? "X" : " ";
        return "[" + "T" + "]"
                + "[" + taskStatus + "]"
                + " " + this.getDescription();
    }

}
