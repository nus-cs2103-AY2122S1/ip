public class Task {
    protected Boolean done;
    protected String name;
    protected char type;

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

    @Override
    public String toString() {
        return "[" + getStatus() + "] " + name;
    }
}
