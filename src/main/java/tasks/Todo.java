package tasks;

import tasks.Task;
import tasks.Todo;

public class Todo extends Task {


    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the string representation of the Todo object.
     *
     * @return string representation of the Todo object.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
