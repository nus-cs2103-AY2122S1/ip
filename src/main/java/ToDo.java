public class ToDo extends Task {
    public ToDo(String name) {
        super(name);
    }

    @Override
    public String toString() {
        String isDone = isDone() ? "x" : " ";
        return String.format("[T][%s] %s", isDone, getName());
    }
}
