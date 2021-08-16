public class Task {
    private String name;
    private boolean completed;

    public Task(String name) {
        this.name = name;
        this.completed = false;
    }

    public String getName() {
        return name;
    }

    public boolean completeTask() {
        this.completed = true;
        return true;
    }

    public boolean isCompleted() {
        return this.completed;
    }
}
