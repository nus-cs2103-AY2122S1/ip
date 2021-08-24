package duke.stubs;

import duke.testinginterface.TodoInterface;
import duke.task.Task;

/**
 * Stub for Todo class.
 */
public class TodoStub extends Task implements TodoInterface {
    public TodoStub() {
        super("");
    }

    @Override
    public String toString() {
        return "[T][ ] Create a todo task";
    }

    public int taskType() {
        return 0;
    }

    public String toSavedFormat() {
        return "T/~/0/~/Create a todo task";
    }
}
