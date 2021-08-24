package duke.stubs;

import duke.task.Task;
import duke.testinginterface.DeadlineInterface;


public class DeadlineStub extends Task implements DeadlineInterface {
    public DeadlineStub() {
        super("");
    }

    @Override
    public String toString() {
        return "[D][ ] Create a deadline task (by: Dec 4 2021)";
    }

    public int getTaskType() {
        return 1;
    }

    public String toSavedFormat() {
        return "D/~/0/~/Create a deadline task/~/2021-12-04";
    }
}
