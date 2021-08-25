package task;

import duke.*;
import ui.*;

/**
 * A type of task that has no additional arguments and extends from Task
 *
 * @author: Wei Yangken
 */

public class Todo extends Task{

    private static String taskCat = "todo";

    /**
     * Constructor to create a TODO task
     * @param name Name of task
     */
    public Todo(String name, boolean isDone) {
        super(name, taskCat, isDone);
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

    /**
     * @return Category of task
     */
    @Override
    public String getTaskCat() {
        return taskCat;
    }
}
