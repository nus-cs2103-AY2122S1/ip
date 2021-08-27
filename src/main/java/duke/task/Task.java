package duke.task;

import java.time.LocalDate;
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
     * @param bool Changes the completion status to the param bool.
     */
    public void changeIsDone(boolean bool) {
        isDone = bool;
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
        return this.itemName.equals(other.itemName) && this.dateTime.equals(other.dateTime) && (this.isDone == other.isDone);
    }
}