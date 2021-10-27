package duke.task;

/**
 * Task class which encapsulates task description and status of completion.
 */
public class Task {
    private String description;
    private boolean isDone;

    /**
     * Constructor method of Task.
     *
     * @param description Description of a task.
     */
    public Task(String description) {
        assert description != null : "Task description should not be null";
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the description of a task.
     *
     * @return Description of a task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns true if the task is completed and false otherwise.
     *
     * @return Boolean indicator for whether a task is done or not.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Marks task status as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Returns the task in array format.
     *
     * @return Task in string array format.
     */
    public String[] formatTaskInArray() { 
        String doneIndicator; 
        if (isDone) {
            doneIndicator = "1";
        } else {
            doneIndicator = "0";
        }
        String[] str = new String[]{"Task", doneIndicator, description};
        return str;
    }

    @Override
    public String toString() {
        if (!isDone) {
            return "[ ] " + description;
        } else {
            return "[X] " + description;
        }
    }
}