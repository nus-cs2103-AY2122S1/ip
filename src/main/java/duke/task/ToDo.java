package duke.task;

import duke.Duke;


/**
 * <code>ToDo</code> subclass is almost identical to a Task Class
 * The [T] in toString() identifies a To-Do object.
 */

public class ToDo extends Task {
    public ToDo(String description, boolean isDone){
        super(description, isDone);
    }

    @Override
    public String toString(){
        return "[T] " + "[" + this.getStatusIcon() + "] " + this.description;
    }

}
