public class Task {
    protected boolean done = false;
    protected String name;

    public Task(String name) {
        this.name = name;
    }

    public void Done() {
        this.done = true;
    }

    @Override
    public String toString() {
        if (done) {
            return "[X] " + name;
        } else {
            return "[ ] " + name;
        }
    }
}
