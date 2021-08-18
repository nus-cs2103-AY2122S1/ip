/**
 * This class encapsulates a Task object.
 */

public class Task {
    protected String description;
    protected boolean isDone;
    protected String taskType;
    protected String reminder;

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
     * Creates a task with the specified description, isDone false by default.
     * Overloaded constructor method that includes reminder, to be used for deadline and event tasks.
     *
     * @param description description of task
     * @param taskType    type of task (todo/deadline/event)
     */
    public Task(String description, String taskType, String reminder) {
        this.description = description;
        this.isDone = false;
        this.taskType = taskType;
        this.reminder = reminder;
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
     * Getter method for task reminder
     *
     * @return the string reminder for the task
     */
    public String getReminder() {
        return this.reminder;
    }
    /**
     * Setter method for isDone of a Task object
     */
    public void markAsDone() {
        this.isDone = true;
    }
}
