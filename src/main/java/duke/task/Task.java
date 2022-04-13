package duke.task;

import java.time.LocalDateTime;

/**
 * A task is an item written by the user and consists of the name of the task,
 * whether it is done or not and the time in which it needs to be done by(when applicable).
 */
public class Task {

    private String itemName;
    private boolean isDone = false;
    private LocalDateTime dateTime;

    public Task(String str) {
        itemName = str;
    }

    /**
     * Initialises a Task.
     *
     * @param str String that tells us the task description.
     * @param dateTime the Date that the task is happening.
     */
    public Task(String str, LocalDateTime dateTime) {
        itemName = str;
        this.dateTime = dateTime;
    }

    /**
     * Returns the task title when needed.
     *
     * @return the task title.
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * Checks whether the task is completed.
     *
     * @return the task completion status.
     */
    public boolean getIsDone() {
        return isDone;
    }

    /**
     * Changes the completion status of the task.
     *
     * @param doneStatus Changes the completion status to true if doneStatus is 1.
     */
    public void changeIsDone(String doneStatus) {
        if (doneStatus.equals("1")) {
            isDone = true;
        }
    }

    @Override
    public String toString() {
        return "[" + (isDone ? "X" : " ") + "] " + itemName;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Task)) {
            return false;
        }
        Task other = (Task) obj;
        boolean isItemNameEqual = this.itemName.equals(other.itemName);

        boolean isDateTimeEqual = this.dateTime.equals(other.dateTime);
        boolean isDoneEqual = this.isDone == other.isDone;
        return isItemNameEqual && isDateTimeEqual && isDoneEqual;
    }
}
