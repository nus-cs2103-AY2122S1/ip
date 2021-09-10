package side.tasks;

/**
 * Represents a task that has to be done.
 *
 * It encapsulates the following information:
 * - description
 * - time
 * - isDone
 *
 * @author Lua Yi Da
 */

public class Task {

    protected String description;
    protected boolean isDone;

    /**
     * Initialises a new Task.
     *
     * @param description String for description given.
     */
    public Task(String description) {
        this.description = description.trim() + " ";
        this.isDone = false;
    }

    /**
     * Initialises a new Task.
     *
     * @param description String for description given.
     * @param isDone boolean describing if Task is done or not.
     */
    public Task(String description, boolean isDone) {
        this.description = description.trim() + " ";
        this.isDone = isDone;
    }

    /**
     * Gets description from Task instance.
     *
     * @return String for description of instance.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Gets isDone from Task instance.
     *
     * @return boolean for isDone of instance.
     */
    public boolean getIsDone() {
        return this.isDone;
    }

    /**
     * Marks a task as done.
     *
     * @return String response of Side in response to marking a task as done.
     */
    public String markAsDone() {
        if (!this.isDone) {
            this.isDone = true;
            return "Fine, I'll mark it for you: " + this.toString();
        } else {
            return "I'm lazy, stop making me mark the same things again...";
        }
    }

    /**
     * Overrides toString method to create string matching list format.
     *
     * @return String matching list format.
     */
    @Override
    public String toString() {
        StringBuilder taskString = new StringBuilder();
        if (this.isDone) {
            taskString.append("[T][x]");
        } else {
            taskString.append("[T][ ]");
        }
        taskString.append(this.description.replaceFirst("todo", ""));
        return taskString.toString();
    }
}
