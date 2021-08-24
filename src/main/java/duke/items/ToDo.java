package duke.items;

public class ToDo extends Item {
    public ToDo(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
