package duke.stubs;

import duke.task.Task;
import duke.testinginterface.TodoInterface;

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

    public char getTaskType() {
        return 'T';
    }

    public String toSavedFormat() {
        return "T/~/0/~/Create a todo task";
    }
}
