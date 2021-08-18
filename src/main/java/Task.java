/**
 * This class implements a Task to be stored in a DukeList
 *
 * @author Amos Tan
 * @version CS2103T AY21/22 Semester 1
 */

public abstract class Task {
    /** The boolean to mark whether a task is completed.*/
    protected boolean isDone;
    /** The name of the task */
    protected String name;

    /**
     * Constructor of the task
     * @param name name of the task
     */
    public Task(String name)
    {
        this.name = name;
        setDone(false);
    }

    /**
     * Setter method to set a task as done or undone
     * @param doneOrNot the boolean to set the task as
     */
    public void setDone(boolean doneOrNot)
    {
        isDone = doneOrNot;
    }

    /**
     * Returns a string representation of the task, with an [X] marked for done and [ ] as undone
     * @return the string representation of the task
     */
    @Override
    public String toString()
    {
        return String.format("[" + (isDone ? "X" : " ") + "] " + name);
    }

}
