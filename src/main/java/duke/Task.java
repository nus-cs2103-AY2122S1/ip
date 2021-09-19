package duke;

/**
 * Represents a task
 */
public abstract class Task {
    /** The name of the Task */
    protected String name;
    /** Whether the Task is done or not */
    protected boolean isDone;
    /** Whether this Task has a timed entry or not */
    private boolean isTimed;

    /**
     * Class constructor
     * @param name The name of the Task
     * @param isTimed Whether this Task has a timed entry or not
     */
    public Task(String name, boolean isTimed){
        assert !name.equals(""); // Name should not ever be empty
        this.name = name;
        this.isTimed = isTimed;
        this.isDone = false;
    }

    /**
     * Class constructor
     * @param name The name of the Task
     * @param isTimed Whether this Task has a timed entry or not
     */
    public Task(String name, boolean isTimed, boolean isDone){
        assert !name.equals("");
        this.name = name;
        this.isDone = isDone;
    }

    /**
     * Gets the name of the Task
     * @return The name of the Task
     */
    public String getName(){
        return name;
    }

    /**
     * Mark this task as finished
     */
    public void markDone(){
        this.isDone = true;
    }

    /**
     * Converts the Task into a String that represents this Task in a CSV file
     * @return A String representing this Task as a CSV row
     */
    public abstract String toCsvRow();

    /**
     * Whether this Task is timed or not
     * @return <code>isTimed</code>
     */
    public boolean isTimed(){
        return isTimed;
    }

    @Override
    public String toString(){
        if (this.isDone){
            return "[X] " + name;
        } else {
            return "[ ] " + name;
        }
    }
}