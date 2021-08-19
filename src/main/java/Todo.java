/**
 * Class for todo task
 *
 * @author  Yim Jaewon
 */
public class Todo extends Task {

    /**
     * Basic constructor of the class that just uses a super constructor.
     *
     * @param description The description of the task
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * override toString method for easier printing
     *
     * @return the stingified task
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}