public class Task {
    private String name;
    private boolean hasDone = false;

    public Task(String name) {
        this.name = name;
    }

    public void markAsDone() {
        this.hasDone = true;
    }

    public String getName() {
        return this.name;
    }

    public boolean getStatus() {
        return this.hasDone;
    }
}
