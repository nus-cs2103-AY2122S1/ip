/**
 * Class for Todo, a child class of Task.
 * @author Liew Jian Hong
 */
public class Todo extends Task{

    /**
     * Constructor for a Todo task.
     * @param desc String array consisting of parsed description.
     */
    public Todo(String[] desc) {
        super(desc[1], false);
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
