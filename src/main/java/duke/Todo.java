package duke;

/**
 * Class for duke.Todo, a child class of duke.Task.
 * @author Liew Jian Hong
 */
public class Todo extends Task{

    /**
     * Constructor for a duke.Todo task.
     * @param desc String array consisting of parsed description.
     */
    public Todo(String[] desc) {
        super(desc[1], Boolean.valueOf(desc[3]));
    }
    public String toWrite() {
        return "todo--" + Boolean.toString(this.isDone) + "--" + this.desc + "--0\n";
    }
    /**
     * Return a string representation of the todo.
     * @return Return the type, completion status and description of the todo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString() + "\n";
    }
}
