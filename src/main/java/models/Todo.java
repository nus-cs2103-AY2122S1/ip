package models;

/**
 * Todo class that represents a Todo that will be saved by Dub.
 */
public class Todo extends Task {

    /**
     * Constructor of the Todo class.
     *
     * @param description Description of the Todo.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a copy of the current task.
     *
     * @return A copy of the task.
     */
    public Todo copyTask() {
        Todo task = new Todo(description);
        task.isDone = isDone;
        return task;
    }

    /**
     * Returns true if two objects are equal.
     *
     * @param obj Object that will be compared.
     * @return True if the object are equal.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (obj instanceof Todo) {
            Task temp = (Todo) obj;
            return temp.toString().equals(this.toString());
        }

        return false;
    }

    /**
     * Return string implementation of the Todo object.
     *
     * @return String implementation of the Todo.
     */
    @Override
    public String toString() {
        if (this.isDone) {
            return "[T][X] " + this.description;
        }
        return "[T][ ] " + this.description;
    }
}
