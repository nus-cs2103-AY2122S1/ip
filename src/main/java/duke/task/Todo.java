package duke.task;

/**
 * This class is a subclass of task.
 *
 * @author Deng Huaiyu(G12)
 * @version CS2103T AY21/22 Semester 1
 */
public class Todo extends Task{

    /**
     * The construction method for a todo.
     *
     * @param description detail of a todo
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * The toString method to output a todo.
     *
     * @return return the string form of the todo
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * The method is to get type of the todo task
     *
     * @return type of the task
     */
    @Override
    public String getType() {
        return "T";
    }
}
