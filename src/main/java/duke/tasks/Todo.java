package main.java.duke.tasks;

/**
 * A todo task.
 */
public class Todo extends Task {
    protected String prefix;

    /**
     * Constructs a new todo task
     * @param name name of the task
     */
    public Todo(String name) {
        super(name);
        this.prefix = "[T]";
    }

    @Override
    public String toString() {
        return (this.prefix + " " + super.showStatus() + this.name + "\n");
    }

}
