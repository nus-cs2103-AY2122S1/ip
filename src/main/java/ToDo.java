public class ToDo extends Task {
    public ToDo(String name) {
        super(name);
    }

    @Override
    public boolean isExpired() {
        return false;
    }

    @Override
    public String toString() {
        return "[T] " + super.toString();
    }
}
