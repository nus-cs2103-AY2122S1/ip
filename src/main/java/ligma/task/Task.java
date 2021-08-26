package ligma.task;

import java.util.function.Predicate;

public class Task {
    public enum TaskType {
        TODO, EVENT, DEADLINE
    }
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public Task markAsDone() {
        this.isDone = true;
        return this;
    }

    public String getTaskDesc() {
        return description;
    }

    public boolean match(String target) {
        return description.contains(target);
    }

    @Override
    public String toString() {
        String check = isDone ? "[X] " : "[ ] ";
        return check + description;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Task) {
            return description.equals(((Task) obj).description);
        }
        return false;
    }
}