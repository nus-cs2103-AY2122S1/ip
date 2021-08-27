package duke;

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

    @Override
    public String saveString() {
        return "T" + super.saveString();
    }
}
