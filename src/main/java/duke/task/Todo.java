package duke.task;

public class Todo extends Task {
    private static final String symbol = "[T]";
    public Todo(String action) {
        super(action);
    }

    @Override
    public String toSaveFormat() {
        return String.format("%s||%s||%s", symbol, super.isComplete(), super.getAction());
    }

    @Override
    public String toString() {
        return String.format("%s%s", symbol, super.toString());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Todo) {
            Todo otherTodo = (Todo) obj;
            return otherTodo.toSaveFormat().equals(this.toSaveFormat());
        }
        return false;
    }
}
