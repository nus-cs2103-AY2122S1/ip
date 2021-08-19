/**
 * Description:
 * ToDos: tasks without any date/time attached to it e.g., visit new theme park.
 *
 * @author Leong Hong Fai
 */

public class ToDo extends Task {

    public ToDo(String name) {
        super(name);
    }

    /**
     * Simple string representation of ToDo.
     *
     * @return A string consisting of the information of the ToDo object.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}