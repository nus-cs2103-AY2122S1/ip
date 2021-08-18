/**
 * This class encapsulates a Task object.
 */

public class Task {
    protected String description;
    protected boolean isDone;
    protected String taskType;

    /**
     * Creates a task with the specified description, isDone false by default.
     *
     * @param description description of task
     * @param taskType    type of task (todo/deadline/event)
     */
    public Task(String description, String taskType) {
        this.description = description;
        this.isDone = false;
        this.taskType = taskType;
    }

    /**
     * Getter method for status using isDone
     *
     * @return an icon 'X' if complete, space if otherwise
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Getter method for type of task using taskType.
     *
     * @return an icon representing the type of the task (T, D, E)
     */
    public String getTypeIcon() {
        return (this.taskType == "todo" ? "T" :
                this.taskType == "deadline" ? "D" :
                        this.taskType == "event" ? "E" : "NONE");
    }

    /**
     * Setter method for isDone of a Task object
     */
    public void markAsDone() {
        this.isDone = true;
    }
}
