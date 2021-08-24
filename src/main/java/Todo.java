public class Todo extends Task{
    public Todo(String str) {
        super(str);
    }

    public Todo(String isDone, String desc) {
        super(isDone, desc);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String saveString() {
        return "T|" + super.saveString();
    }
}
