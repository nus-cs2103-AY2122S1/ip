public abstract class Task {

    String name;
    boolean isDone;

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public void setCompleted() {
        this.isDone = true;
    }

    public abstract String toString();

}
