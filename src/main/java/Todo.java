/**
 * a class to encapsulate tasks without any date/time attached.
 */

public class Todo extends Task {

    /**
     * constructor for Todo class.
     * @param description the task description.
     */

    public Todo(String description) {
        super(description);
    }

    /**
     * method to print out the Todo task,
     * overrides toString in Task.
     * @return string format of the todo task, consisting of
     * the task marker "[T]" and task description.
     */

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
