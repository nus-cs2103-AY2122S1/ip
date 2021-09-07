package duke.task;

/**
 * Represents a duke.task which can be marked done.
 *
 * @author Gordon Yit
 * @version CS2103T, Semester 2
 */

public class Task {
    protected boolean isDone;

    /**
     * Class constructor for Duke.Task class.
     * Sets isDone to false, meaning duke.task is not done.
     */
    public Task() {
        this.isDone = false;
    }

    /**
     * Returns the status icon of the duke.task.
     *
     * @return "X" if duke.task is done, else returns " ".
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done duke.task with X
    }

    /**
     * Marks duke.task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Prints out the duke.task.
     *
     * @return string format of the duke.task,
     * consisting of the status icon and duke.task description.
     */
    @Override
    public String toString() {
        return String.format("[%s]", getStatusIcon());
    }

    /**
     * Formats the duke.task in to the storage format.
     *
     * @return storage format of the duke.task.
     */
    public String formatToStore() {
        return String.format("%s |", getStatusIcon() == " " ? 1 : 0);
    }

    /**
     * Returns duke.task marker.
     *
     * @return a one character string that is a marker for this duke.task.
     */
    public String getTaskMarker() {
        return "";
    }

    /**
     * Checks if given datetime matches the deadline of the duke.task.
     *
     * @param dateString date in string form to to compare with.
     * @return false.
     */
    public boolean isSameDate(String dateString) {
        return false;
    }

}
