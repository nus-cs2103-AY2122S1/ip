package duke.stubs;

import duke.task.Task;
import duke.testinginterface.DeadlineInterface;


/**
 * Stub for Deadline class.
 */
public class DeadlineStub extends Task implements DeadlineInterface {
    /**
     * Constructor.
     */
    public DeadlineStub() {
        super("");
    }

    @Override
    public String toString() {
        return "[D][ ] Create a deadline task (by: Dec 4 2021)";
    }

    public char getTaskType() {
        return 'D';
    }

    public String toSavedFormat() {
        return "D/~/0/~/Create a deadline task/~/2021-12-04";
    }
}
