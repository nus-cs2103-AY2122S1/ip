package duke.data.tasks;

/**
 * Represents a task, which has a description, a completion status and an ID.
 */
public abstract class Task implements Comparable<Task> {
    private static int count = 0;
    private boolean completed;
    private final String name;
    private final int id;

    public Task(String name) {
        this.name = name;
        this.id = count++;
        this.completed = false;
    }

    public Task(boolean completed, String name) {
        this.name = name;
        this.id = count++;
        this.completed = completed;
    }

    protected String getName() {
        return this.name;
    }

    public String completeTask() {
        this.completed = true;
        return this.toString();
    }

    protected boolean isCompleted() {
        return this.completed;
    }

    public abstract String getSaveData();

    @Override
    public String toString() {
        if (this.completed) {
            return "[X] " + this.name;
        } else {
            return "[ ] " + this.name;
        }
    }

    @Override
    public int compareTo(Task other) {
        return Integer.compare(this.id, other.id);
    }

    @Override
    abstract public boolean equals(Object o);
}
