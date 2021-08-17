public class Todo extends Entry {

    Todo() {
        super();
    }

    Todo(String task) {
        super(task);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
