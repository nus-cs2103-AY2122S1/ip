package duke.task;

/**
 * class to represent todos, a type of task
 *
 */
public class Todo extends Task {

    /**
     * Constructor for Todo that does not specify whether task is done
     * Sets done to false by default
     *
     * @param title name of todo
     */
    public Todo(String title) {
        super(title);
    }

    /**
     * Constructor for Todo that does specify whether task is done
     *
     * @param title name of todo
     * @param isDone whether task is done or not
     */
    public Todo(String title, Boolean isDone) {
        super(title, isDone);
    }

    public void updateDate(String date) {}

    /**
     * Returns a string representation of the todo task for displaying in ui
     *
     * @return string representing todo task for displaying in ui
     */
    @Override
    public String toString() {
        if (this.isDone) {
            return "[T][X] " + this.title;
        } else {
            return "[T][ ] " + this.title;
        }
    }

    /**
     * Resturns a string representation of the todo task for saving
     *
     * @return string representation of todo task for saving to storage
     */
    @Override
    public String saveString() {
        if (this.isDone) {
            return "T : 1 : " + this.title;
        } else {
            return "T : 0 : " + this.title;
        }
    }
}
