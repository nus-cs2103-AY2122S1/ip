public class ToDo extends Task {

    ToDo(String name) {
        super(name);
    }

    ToDo(String name, boolean isDone) {
        super(name, isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
