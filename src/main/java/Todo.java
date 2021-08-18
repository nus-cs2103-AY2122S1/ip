public class Todo extends Task {
    public Todo(String str) throws DukeException {
        super(str);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}