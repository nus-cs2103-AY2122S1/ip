package model.task;

/**
 * Class for simple To-Do task
 *
 * @author Kan Jitpakdi
 * @author GitHub: kanjitp
 * @version 0.02
 * @since 0.01
 */
public class Todo extends Task {
    /**
     * Default constructor for To-Do task.
     *
     * @param description the description of the To-Do task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Default constructor for To-Do task.
     *
     * @param description the description of the To-Do task.
     * @param isDone      whether the task is done.
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Template: "[T][x] description" or "[T][ ] description" for done and not done task respectively.
     *
     * @return the template above for To-Do task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
