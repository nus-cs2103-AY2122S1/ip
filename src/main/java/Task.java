public abstract class Task {
    protected boolean done;
    protected String name;

    public Task(String name) {
        this.name = name;
        this.done = false;
    }

    public void doTask() {
        done = true;
    }

    @Override
    public String toString() {
        return "[" + (done ? "X" : " ") + "] " + name;
    }

    public String getSaveString() {
        return (done ? "o," : "x,") +  name;
    }
}
