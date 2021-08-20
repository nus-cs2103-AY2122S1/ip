public class Todo extends Task {

    public Todo(String todoName) {
        super(todoName.substring(5));
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
