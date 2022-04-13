package duke.items;

/**
 * Represents a normal entry in a to-do list. It has
 * a name.
 */
public class ToDo extends Item {
    public ToDo(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
