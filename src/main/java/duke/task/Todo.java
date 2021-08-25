package duke.task;

import duke.task.Task;

public class Todo extends Task {
    private final static String symbol = "[T]";
    public Todo(String action) {
        super(action);
    }

    public String toSaveFormat() {
        return String.format("%s||%s||%s", symbol, super.isComplete(), super.getAction());
    }

    public String toString() {
        return String.format("%s%s", symbol, super.toString());
    }
}
