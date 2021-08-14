package components;

import exceptions.DukeEmptyStringException;

public class ToDo extends Task{

    private ToDo(String taskDescription, boolean done) {
        super(taskDescription, done);
    }

    public static ToDo of(String taskDescription, boolean done) throws DukeEmptyStringException {
        return new ToDo(taskDescription, done);
    }

    @Override
    public ToDo markDone() {
        return new ToDo(taskDescription, true);
    }

    @Override
    public String toString() {
        if (this.done) {
            return String.format("[T] [X] %s", this.taskDescription);
        } else {
            return String.format("[T] [ ] %s", this.taskDescription);
        }
    }
}
