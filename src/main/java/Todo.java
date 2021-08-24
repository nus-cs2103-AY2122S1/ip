public class Todo extends Task{

    public Todo(String name) {
        super(name);
    }

    @Override
    public String write() {
        return "T " + super.write();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
