package task;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * A type of task that has no additional arguments and extends from Task
 *
 * @author: Wei Yangken
 */
public class Todo extends Task {

    private static final String TASK_CAT = "todo";
    private ArrayList<Tag> tags;

    /**
     * Constructor to create a TODO task
     * @param name Name of task
     */
    public Todo(String name, boolean isDone) {
        super(name, TASK_CAT, isDone);
        this.tags = new ArrayList<>();
    }

    public Todo(String name, boolean isDone, ArrayList<Tag> tags) {
        super(name, TASK_CAT, isDone);
        this.tags = tags;
    }

    /**
     * Returns the name of the task in a format that shows type of task and its completion status
     * @return Task as a formatted string
     */
    @Override
    public String toString() {
        if (this.isDone()) {
            return String.format("[T][X] %s", this.getName());
        } else {
            return String.format("[T][ ] %s", this.getName());
        }
    }

    /**
     * @return Category of task
     */
    @Override
    public String getTaskCat() {
        return TASK_CAT;
    }

    /**
     * @param o Object to be compared to
     * @return Whether the two tasks share the same name
     */
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Todo)) {
            return false;
        }
        Todo todo = (Todo) o;

        return todo.getName().equals(this.getName());
    }
}
