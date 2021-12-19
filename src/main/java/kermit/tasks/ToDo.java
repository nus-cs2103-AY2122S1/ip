package kermit.tasks;


/**
 * A basic task. That tracks if a task is complete.
 */
public class ToDo extends Task {
    /**
     * Constructs ToDo task.
     *
     * @param description Description of task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Constructs ToDo task.
     *
     * @param description Description of task.
     * @param isCompleted Boolean to set if task is completed.
     */
    public ToDo(String description, boolean isCompleted) {
        super(description, isCompleted);
    }


    /**
     * Gets abbreviation of task, usually first letter.
     *
     * @return String abbreviation of task.
     */
    @Override
    public String getShortForm() {
        return "T";
    }
}
