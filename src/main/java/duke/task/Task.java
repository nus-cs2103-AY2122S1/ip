package duke.task;

public class Task {
    protected boolean completed = false;
    protected String name;

    public Task(String name) {
        this.name = name;
        this.completed = false;
    }

    public void markComplete() {
        this.completed = true;
    }

    private String getStatusIcon() {
        return this.completed ? "X" : " ";
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.name);
    }

    public String toCommand(int index) {
        return this.completed ? String.format("done %d\n", index) : "";
    };
}