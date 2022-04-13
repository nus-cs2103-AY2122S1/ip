package duke.task;

import java.util.ArrayList;

public class Todo extends Task {
    /**
     * Constructor for a `Todo` task.
     *
     * @param isDone      Indicates whether the task has been completed.
     * @param description Task description.
     */
    public Todo(boolean isDone, String description) {
        super(isDone, description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String saveAsData() {
        return 0 + "\n" + super.saveAsData() + "\n";
    }

    @Override
    public boolean checkIfAlreadyAdded(ArrayList<Task> list) {
        boolean hasBeenAdded = false;

        for (Task task : list) {
            if (this.equals(task)) {
                hasBeenAdded = true;
                break;
            }
        }
        return hasBeenAdded;
    }
}
