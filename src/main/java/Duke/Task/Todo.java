package Duke.Task;

import Duke.Main.DukeException;

public class Todo extends Task {

    private String taskDescription;

    /**
     * Constructor of a TODO task
     * @param taskName name of the task
     * @throws DukeException if the description is incompleted
     */
    public Todo(String taskName) {
        super(taskName);
        if (taskName.length() == 0) {
            throw new DukeException("Error: ", DukeException.TYPE.INCOMPLETE);
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
