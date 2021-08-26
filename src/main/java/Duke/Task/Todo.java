package Duke.Task;

import Duke.DukeException.DukeIncompleteException;

public class Todo extends Task {
    /**
     * Constructor of a TODO task
     * @param taskName name of the task
     */
    public Todo(String taskName) {
        super(taskName);
        if (taskName.length() == 0) {
            throw new DukeIncompleteException();
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
}
