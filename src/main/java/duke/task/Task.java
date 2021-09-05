package duke.task;

import javax.xml.stream.FactoryConfigurationError;
import java.time.LocalDate;

/**
 * Represents a task which can be marked done.
 * 
 * @author Gordon Yit
 * @version CS2103T, Semester 2
 */

public class Task {
    protected boolean isDone;

    /**
     * Class constructor for Duke.Task class.
     * Sets isDone to false, meaning task is not done.
     *
     */
    public Task() {
        this.isDone = false;
    }
    
    /**
     * Returns the status icon of the task.
     * 
     * @return "X" if task is done, else returns " ".
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Prints out the task.
     * 
     * @return string format of the task, 
     * consisting of the status icon and task description.
     */
    @Override
    public String toString() {
        return String.format("[%s]", getStatusIcon());
    }

    /**
     * Formats the task in to the storage format.
     * 
     * @return storage format of the task.
     */
    public String formatToStore() {
        return String.format("%s |", getStatusIcon() == " " ? 1 : 0);
    }
    
    /**
     * Returns task marker. 
     *
     * @return a one character string that is a marker for this task.
     */
    public String getTaskMarker() {
        return "";
    }

    /**
     * Checks if given datetime matches the deadline of the task.
     * 
     * @param dateString date in string form to to compare with.
     * @return false.
     */
    public boolean isSameDate(String dateString) {
        return false;
    }

}
