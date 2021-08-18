/**
 * This class implements a DukeList to be used in storing string from Duke
 *
 * @author Amos Tan
 * @version CS2103T AY21/22 Semester 1
 */

public class ToDo extends Task {

    /**
     * Constructor for ToDo task
     * @param name the name of the todo task
     */
    public ToDo(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return String.format("[T]" + super.toString());
    }
}
