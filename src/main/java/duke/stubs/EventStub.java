package duke.stubs;

import duke.task.Task;
import duke.testinginterface.EventInterface;

/**
 * Stub for Event class.
 */
public class EventStub extends Task implements EventInterface {
    public EventStub() {
        super("");
    }

    @Override
    public String toString() {
        return "[E][ ] Create an event task (at: Dec 4 2021)";
    }

    public char getTaskType() {
        return 'E';
    }

    public String toSavedFormat() {
        return "E/~/0/~/Create an event task/~/2021-12-04";
    }
}
