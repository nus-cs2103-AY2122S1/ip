package back;

public class Todo extends Task {

    /**
     * Todo constructor.
     *
     * @param taskName Name of task.
     */
    public Todo(String taskName) {
        super(taskName);
    }

    /**
     * Returns a string representation of this todo task.
     */
    @Override
    public String toString() {
        return super.toString() + " (TODO)";
    }

    /**
     * Returns if the object provided is the same as this.
     * @param o Object to be queried.
     * @return True if the object provided is the same, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof Task) {
            Task t = (Task) o;
            boolean nameEquals = t.taskName.equals(taskName);
            boolean donenessEquals = t.isDone == isDone;
            return (nameEquals && donenessEquals);
        } else {
            return false;
        }
    }
}