package duchess.task;

import java.time.LocalDateTime;

/**
 * This class implements a Task to be stored in a DukeList
 *
 * @author Amos Tan
 * @version CS2103T AY21/22 Semester 1
 */

public abstract class Task {
    /** The boolean to mark whether a task is completed.*/
    protected boolean isDone;
    /** The name of the task.*/
    protected String name;

    /**
     * Constructor of the task.
     * @param name The name of the task.
     */
    public Task(String name)
    {
        this.name = name;
        setDone(false);
    }

    /**
     * Setter method to set a task as done or undone.
     * @param doneOrNot The boolean to set the task as.
     */
    public void setDone(boolean doneOrNot)
    {
        isDone = doneOrNot;
    }

    public boolean isDone() {
        return isDone;
    }

    public abstract LocalDateTime getDateTime();

    /**
     * Returns a simplified representation of the task for easier recovery from save file.
     * @return The file formatted string representation of the task.
     */
    public abstract String toFileFormat();

    /**
     * Returns a string representation of the task, with an [X] marked for done and [ ] as undone.
     * @return the string representation of the task.
     */
    @Override
    public String toString()
    {
        return String.format("[" + (isDone ? "X" : " ") + "] " + name);
    }

}
