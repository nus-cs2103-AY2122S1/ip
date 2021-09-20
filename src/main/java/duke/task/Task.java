package duke.task;

import duke.util.DukeException;

/**
 * Class that encapsulates a task.
 */
public class Task {
    private String taskName;
    private boolean isDone;

    /**
     * Returns a new Task object.
     * @param taskName The user input.
     */
    public Task(String taskName, int... pos) throws DukeException {
        boolean hasEnd = pos.length > 1;

        if (hasEnd) {
            this.taskName = taskName.substring(pos[0], pos[1]);
        } else {
            this.taskName = taskName.substring(pos[0]);
        }

        if (this.taskName.equals("")) {
            throw new DukeException("OOPS! Task description cannot be empty!");
        }

        this.isDone = false;
    }

    /**
     * Returns a new Task object.
     * @param taskName The user input.
     * @param isDone Whether the task is done.
     */
    public Task(String taskName, boolean isDone, int... pos) {
        boolean hasEnd = pos.length > 1;
        if (hasEnd) {
            this.taskName = taskName.substring(pos[0], pos[1]);
        } else {
            this.taskName = taskName.substring(pos[0]);
        }
        this.isDone = isDone;
    }


    public String getTaskName() {
        return taskName;
    }

    public void setDone() {
        this.isDone = true;
    }

    public void setTaskName(String name) {
        this.taskName = name;
    }

    /**
     * Overrides the toString method in Object.
     * @return The String representation of the Task object.
     */
    @Override
    public String toString() {
        if (this.isDone) {
            return "[X] " + this.taskName;
        } else {
            return "[ ] " + this.taskName;
        }
    }
}
