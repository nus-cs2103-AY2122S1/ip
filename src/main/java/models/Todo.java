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
     * Equals function that check whether 2 objects are equal or not.
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
            return temp.toString() == this.toString();
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
