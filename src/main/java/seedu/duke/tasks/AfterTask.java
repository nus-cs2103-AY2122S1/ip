package seedu.duke.tasks;

/**
 * Its a {@code Task} which will be available only if the {@code Task} before
 * this is completed.
 */
public class AfterTask {
    private String description;

    /**
     * Constructor which initialises this {@code AfterTask}.
     * 
     * @param description is the description of this {@code AfterTask}.
     */
    public AfterTask(String description) {
        this.description = description;
    }

    /**
     * Retrieves the description of this {@code AfterTask}.
     * 
     * @return the description of this {@code AfterTask}.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Retrieves the new {@code ToDos} when the {@code Task} before this has
     * completed.
     * 
     * @return a new {@code ToDos} which will be added to the {@code TaskList}.
     */
    public ToDos getTask() {
        return new ToDos(this.description);
    }
}
