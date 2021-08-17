abstract public class Task {
    private boolean isCompleted = false;

    public void setCompleted() {
        this.isCompleted = true;
    }

    public boolean isCompleted() {
        return this.isCompleted;
    }

    abstract public String logo();
}
