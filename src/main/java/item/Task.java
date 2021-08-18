package item;

import exception.BotException;
import exception.EmptyCommandException;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) throws BotException {
        if (description.isEmpty()) {
            throw new EmptyCommandException("task");
        }
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        String s = String.format("[%s] %s", this.getStatusIcon(), this.description);
        return s;
    }
}