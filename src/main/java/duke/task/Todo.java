package duke.task;

/**
 * Todo Task to handle todo Tasks
 */
public class Todo extends Task {
    /**
     * Constructor class for toDo Task
     *
     * @param name The name of the task
     */

    public Todo(String name) {
        super(name);
    }

    /**
     * Returns the string to be written in the txt file
     */
    @Override
    public String write() {
        return "T " + super.write();
    }

    /**
     * Returns the list format of the task
     *
     * @return list format of the task
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
