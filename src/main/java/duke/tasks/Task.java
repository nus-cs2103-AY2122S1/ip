package duke.tasks;

/**
 * Abstract base task created by Duke.
 */
abstract public class Task {

    String name;
    boolean done = false;


    /**
     * Default constructor for a new task.
     *
     * @param name task name.
     */
    public Task(String name){
        this(name,false);
    }

    /**
     * Full constructor with all fields exposed
     *
     * @param name task name
     * @param done done state
     */
    public Task(String name, boolean done){
        this.name = name;
        this.done = done;
    }

    /**
     * Generates the message printed when this task is added.
     *
     * @return added: task name.
     */
    public String addMsg(){
        return "added: " + this.name;
    }

    /**
     * Sets this task status to done.
     */
    public void markDone(){
        this.done = true;
    }

    /**
     * Gets the name for this task.
     *
     * @return task name.
     */
    public String getName(){
        return this.name;
    }

    /**
     * Returns json-like representation of object data.
     *
     * @return String representation of data.
     */
    public abstract String serialize();
    // newline is the only? reliable way to divide data without escape characters

    public String toString(){
        return (this.done? "[X] " : "[ ] ")
            + this.name;
    }
}

