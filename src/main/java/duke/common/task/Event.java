package duke.common.task;

import duke.common.Duke;
import duke.common.enums.TaskField;

/**
 * Task associated with a given start time
 */
public class Event extends Task {
    private String eventTime;

    public Event(String description, String eventTime) {
        super(description);
        this.eventTime = eventTime;
    }

    @Override
    public String update(TaskField taskField, String newItem) throws Duke.DukeException {
        if (taskField == TaskField.DESCRIPTION) {
            return super.update(taskField, newItem);
        }
        if (taskField != TaskField.EVENT_START) {
            throw new Duke.DukeException("Sorry, there does not seem to be such a field in this task.");
        }
        this.eventTime = newItem;
        return this.toString();
    }

    @Override
    public String toString() {
        String stem = super.toString();
        return String.format("[E]%s (at: %s)", stem, this.eventTime);
    }
}
