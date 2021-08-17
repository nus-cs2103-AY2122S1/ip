public class Task {
    private String name;
    private boolean isDone;
    private int index;

    public Task(String name, boolean isDone, int index) {
        this.name = name;
        this.isDone = isDone;
        this.index = index;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public String getName() {
        return name;
    }

    public boolean isDone() {
        return isDone;
    }

    public int getIndex() {
        return index;
    }
}
