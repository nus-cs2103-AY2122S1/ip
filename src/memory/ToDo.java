package memory;

public class ToDo extends Task {
    public ToDo(String entry) {
        super(entry);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
