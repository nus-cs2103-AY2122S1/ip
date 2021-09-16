package duke.task;

/**
 * Description:
 * ToDos: tasks without any date/time attached to it e.g., visit new theme park.
 *
 * @author Leong Hong Fai
 */

public class ToDo extends Task {

    /**
     * Creates ToDo object.
     *
     * @param name name of ToDo task.
     */
    public ToDo(String name) {
        super(name);
        assert !(name == null);
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
