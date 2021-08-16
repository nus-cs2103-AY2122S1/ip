public class Task {
    private String desc;
    private boolean isDone;

    public Task(String desc, boolean isDone) {
        this.desc = desc;
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return String.format("%s\n", this.desc);
    }
}
