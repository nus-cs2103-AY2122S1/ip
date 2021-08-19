/**
 *
 * This class encapsulates a todo task.
 *
 * @author Pawandeep Singh
 * @version Level-4
 * */
public class Todo extends Task {
    public Todo(String task) {
        super(task);
    }


    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
