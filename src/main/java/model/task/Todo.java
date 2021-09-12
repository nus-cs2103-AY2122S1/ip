package model.task;

/**
 * Class for simple To-Do models.task
 *
 * @author Kan Jitpakdi
 * @author GitHub: kanjitp
 * @version 0.02
 * @since 0.01
 */
public class Todo extends Task {
    /**
     * Default constructor for To-Do models.task
     *
     * @param description the description of the To-Do models.task
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Default constructor for To-Do models.task
     *
     * @param description the description of the To-Do models.task
     * @param isDone      whether the models.task is done
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Template: "[T][x] description" or "[T][ ] description" for done and not done models.task respectively.
     *
     * @return the template above for To-Do models.task
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
