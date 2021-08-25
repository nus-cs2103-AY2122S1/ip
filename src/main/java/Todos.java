public class Todos extends Task {

    public Todos(String task) {
        super(task, false);
    }

    public Todos(String task, String isDone) {
        super(task, isDone == "1");
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toSaveString() {
        return "T|" + super.toSaveString();
    }
}
