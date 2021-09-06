package ponyo.data.task;

import ponyo.common.Prefixes;

/**
 * A Todo task object that only has a description.
 */
public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toStringInFile() {
        return Prefixes.PREFIX_TODO + " - " + super.toStringInFile();
    }

    @Override
    public String toString() {
        return "[" + Prefixes.PREFIX_TODO + "]" + super.toString();
    }
}
