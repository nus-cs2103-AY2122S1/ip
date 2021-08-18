public class Todo extends Task{

    /**
     * Constructor to create a TODO task
     * @param name Name of task
     */
    Todo(String name) {
        super(name);
    }

    /**
     * Returns the name of the task in a format that shows type of task and its completion status
     * @return Task as a formatted string
     */
    @Override
    public String toString() {
        if(this.isDone()) {
           return String.format("[T][X] %s", this.getName());
        } else {
            return String.format("[T][ ] %s", this.getName());
        }
    }
}
