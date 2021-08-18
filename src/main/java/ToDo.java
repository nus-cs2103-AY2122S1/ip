/**
 * The ToDo class extends Task and encapsulates
 */

public class ToDo extends Task {
    public ToDo(String message){
        super(message);
    }

    /**
     * Overrides toString() method.
     * @return String representation of the ToDo object.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
