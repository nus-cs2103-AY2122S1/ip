package kermit.tasks;


/**
 * A basic task. That tracks if a task is complete.
 */
public class ToDos extends Task {
    /**
     * ToDo constructor.
     *
     * @param description Description of task.
     */
    public ToDos(String description) {
        super(description);
    }

    /**
     * ToDo constructor.
     *
     * @param description Description of task.
     * @param isCompleted Boolean to set if task is completed.
     */
    public ToDos(String description, boolean isCompleted) {
        super(description, isCompleted);
    }


    /**
     * Get abbreviation of task, usually first letter.
     *
     * @return String abbreviation of task.
     */
    @Override
    public String getShortForm() {
        return "T";
    }
}
