public class ToDo extends Task {

    public ToDo(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String print() {
        return String.format("T,%d,%s",isCompleted() ? 1 : 0, this.getName());
    }
}
