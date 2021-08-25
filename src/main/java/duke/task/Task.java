package duke.task;

import duke.exception.DukeException;

public abstract class Task {
    private String name;
    private boolean isDone;

    protected Task(String name) {
        this(name, false);
    }

    protected Task(String name, boolean isDone) {
        this.name = name;
        this.isDone = isDone;
    }

    protected Task markAsDone() {
        isDone = true;
        return this;
    }

    public String getName() {
        return name;
    }

    public boolean checkTaskDone() {
        return isDone;
    }

    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    public static Task fromText(String text) throws DukeException {
        char taskType = text.charAt(0);
        switch (taskType) {
        case 'T':
            return ToDo.fromText(text);
        case 'D':
            return Deadline.fromText(text);
        case 'E':
            return Event.fromText(text);
        default:
            throw new DukeException(String.format("Cannot parse Task from \n\t`%s`", text));
        }
    }

    public abstract String toText();

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), name);
    }
}
