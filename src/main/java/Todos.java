public class Todos extends Task {

    public Todos(String task) {
        super(task);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toSaveString() {
        return "T | " + super.toSaveString();
    }
}
