public abstract class Task {
    String description;
    Boolean isDone = false;

    public void setDone() {
        isDone = true;
    }
}
