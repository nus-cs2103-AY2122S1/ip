/**
 * CS2103T Individual Project AY 21/22 Sem 1
 * Project Duke
 *
 * Current Progress: Level 4. ToDos, Events, Deadlines
 *
 * Description:
 * Extends the Task Class which where it is a task that does
 * not have a date/time attached to it
 *
 * @author Keith Tan
 */
public class ToDo extends Task {

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
