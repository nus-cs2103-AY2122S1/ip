package main.java.duke.tasks;

/**
 * A task.
 */
public class Task {
    protected String name;
    protected boolean hasDone;
    protected String prefix;

    /**
     * Constructs a new task
     * @param name the task name
     */
    public Task(String name) {
        this.name = name;
        this.hasDone = false;
        this.prefix = "";
    }

    /**
     * mark a task as done and print out relevant information.
     */
    public void markAsDone() {
        this.hasDone = true;
        //System.out.println(this.prefix + "[X] " + this.name);
    }

    /**
     * show the status of the task
     * @return status symbol of the task
     */
    public String showStatus() {
        if (hasDone) {
            return "[X] ";
        } else {
            return "[ ] ";
        }
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return (this.prefix + " " + showStatus() + this.name + "\n");
    }
}
