package duke.task;

/**
 * class to represent todos, a type of task
 *
 */
public class Todo extends Task {

    public Todo(String title) {
        super(title);
    }

    public Todo(String title, Boolean done) {
        super(title, done);
    }

    /**
     * Returns a string representation of the todo task
     *
     * @return string representing todo task
     */
    @Override
    public String toString() {
        if (this.done) {
            return "[T][X] " + this.title;
        } else {
            return "[T][ ] " + this.title;
        }
    }

    @Override
    public String saveString() {
        if (this.done) {
            return "T : 1 : " + this.title;
        } else {
            return "T : 0 : " + this.title;
        }
    }
}
