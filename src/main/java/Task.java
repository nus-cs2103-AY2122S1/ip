public class Task {
    protected String desc;
    protected boolean done;

    public Task(String desc) {
        this.desc = desc;
    }

    public void markComplete() {
        done = true;
    }

    @Override
    public String toString() {
        return (done ? "[X] " : "[ ] ") + desc;
    }

}
