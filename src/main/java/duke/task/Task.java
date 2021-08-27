package duke.task;

public class Task {
    private String name;
    private boolean completed;

    public Task(String name) {
        this.name = name;
        this.completed = false;
    }

    public void markAsDone() {
        this.completed = true;
    }

    public String toSaveFormat() {
        String completedStr = completed ? "1" : "0";
        return completedStr + "|" + name;
    }

    /**
     * Gets the name of the task.
     *
     * @return The name of the task.
     */
    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        String symbol = completed ? "X" : " ";
        return "[" + symbol + "] " + name;
    }
}
