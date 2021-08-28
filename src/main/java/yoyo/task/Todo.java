package yoyo.task;

/**
 * A subclass of Task of todo type.
 */
public class Todo extends Task {

    /**
     * Constructor for the Todo class with name parameter.
     *
     * @param name Name of Todo.
     */
    public Todo(String name) {
        super(name);
    }

    /**
     * Constructor for the Todo class with name and isDone parameter.
     *
     * @param name Name of Todo.
     */
    public Todo(String name, boolean isDone) {
        super(name, isDone);
    }

    /**
     * Returns a status string indicating type of task.
     *
     * @return An indicator string for the type of task.
     */
    @Override
    public String printType() {
        return "[T]";
    }

    /**
     * Produces a string containing task's status.
     *
     * @return a string containing task's status.
     */
    @Override
    public String showStatus() {
        return super.showStatus();
    }


}
