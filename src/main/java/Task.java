public class Task {
    protected Boolean done;
    protected String name;

    public Task(String input) {
        this.name = input;
        this.done = false;
    }

    public String getStatus() {
        return (done ? "X" : " ");
    }

    public Task completeTask() {
        this.done = true;
        return this;
    }

    public String getName() {
        return this.name;
    }
}
