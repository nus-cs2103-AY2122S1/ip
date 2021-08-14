/**
 * This class implements a Todo object which inherits from Task.
 *
 * CS2103T ip
 * AY21/22 Semester 1
 *
 * @author Kishendran Vendar Kon (Group G05)
 */
public class Todo extends Task{
    /** Default constructor. */
    public Todo(String description) {
        super(description);
    }

    /**
     * Return the string representation of Todo
     *
     * @return The string representation of Todo
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
