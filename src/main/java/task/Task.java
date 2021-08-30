package task;

import java.time.LocalDate;

public abstract class Task {
    /** An Enum type of task. **/
    public enum TaskType {
        TODO, DEADLINE, EVENT
    }

    /** A String that stores the description of the task. **/
    private final String description;

    /** A boolean that indicates whether the task is done. **/
    private boolean isDone;

    /**
     * A public constructor to initialize the task.
     *
     * @param description The given description of the task.
     */
    public Task (String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * A public method to get the description of the task.
     *
     * @return A String, the description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * A public method to check whether the task is done.
     *
     * @return A boolean indicating whether the task is done.
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * A public method to generate the status icon of the task.
     *
     * @return A String, the status icon.
     */
    public String getStatusIcon() {
        if (this.isDone) {
            return "X";
        } else {
            return " ";
        }
    }

    /**
     * A public method to set the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * A override toString() method.
     *
     * @return A String. The String representation of the task.
     */
    @Override
    public String toString() {
        return String.format("[%s][%s] %s",
                this.getTypeIcon(), this.getStatusIcon(), this.description);
    }

    /**
     * A method to check whether this task happen on a given date.
     *
     * @param date The given date to check.
     * @return A boolean value indicating whether the task happen on that day.
     */
    public boolean isOnDate(LocalDate date) {
        return false;
    }

    /**
     * A public abstract method to generate the type icon of the task.
     *
     * @return A String. The type icon of the task.
     */
    public abstract String getTypeIcon();
}
