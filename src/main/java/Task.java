public class Task {
    private String desc;
    private boolean done;

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
