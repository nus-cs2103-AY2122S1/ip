package Duke.Task;

import Duke.DukeException.DukeIncompleteException;
import Duke.DukeException.DukeWrongCommandException;

public class Todo extends Task {
    /**
     * Constructor of a TODO task
     * @param taskName name of the task
     */
    public Todo(String taskName) {
        super(taskName);
        if (taskName.length() == 0) {
            throw new DukeIncompleteException();
        } else if (taskName.contains("/by")) {
            throw new DukeWrongCommandException("Deadline");
        } else if (taskName.contains("/at")) {
            throw new DukeWrongCommandException("Event");
        }
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String save() {
        return this.toString();
    }

    @Override
    public String getDate() {
        return "There are no date specified with task ";
    }
}
