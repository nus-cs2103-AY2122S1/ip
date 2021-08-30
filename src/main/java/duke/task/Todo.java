package duke.task;


/**
 * To do class, inherits from Task
 * To do are tasks with just a description
 */
public class Todo extends Task {

    /**
     * Constructor for a to do
     *
     * @param desc The description of the to do
     */
    public Todo(String desc) {
        super(desc);
        this.taskType = "T";
        this.dueDate = "";
    }

    public Todo(String desc, Boolean isDone) {
        super(desc);
        this.taskType = "T";
        this.dueDate = "";
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return "[" + taskType + "]" + super.toString();
    }
}
