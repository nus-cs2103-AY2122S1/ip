package duke.stubs;

import duke.task.Task;
import duke.testinginterface.TodoInterface;

public class TodoStub extends Task implements TodoInterface {
    public TodoStub() {
        super("");
    }

    @Override
    public String toString() {
        return "[T][ ] Create a todo task";
    }

    public int getTaskType() {
        return 0;
    }

    public String toSavedFormat() {
        return "T/~/0/~/Create a todo task";
    }
}
