package duke;
/**
 * Todo class which inherits from a Task.
 */


public class Todo extends Task {


    /**
     * Constructor for a Todo.
     * @param title takes in the title of the Task.
     */
    public Todo(String title) {
        super(title);
    }

    /**
     * The toString method.
     * @return a String describing the Todo.
     */
    @Override
    public String toString() {
        if (!this.getDone()) {
            return "[T][ ]" + this.getTitle();
        } else {
            return "[T][X]" + this.getTitle();
        }

    }
}
