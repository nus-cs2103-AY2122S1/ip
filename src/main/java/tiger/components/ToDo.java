package tiger.components;

import tiger.exceptions.TigerEmptyStringException;

public class ToDo extends Task{

    private ToDo(String taskDescription, boolean done) {
        super(taskDescription, done);
    }

    public static ToDo of(String taskDescription, boolean done) throws TigerEmptyStringException {
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

    protected String getStorageRepresentation() {
        return String.format("T|%s|%s|%s", this.done, this.taskDescription, "");
    }
}
