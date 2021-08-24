public abstract class Task {
    public abstract String showTask();
    public abstract String showTaskOnly();
    public abstract String showType();
    public abstract String showWhen();

    private boolean isDone = false;

    public String checkDone() {
        return isDone ? "[X]" : "[ ]";
    }

    public void isDone() {
        isDone = true;
    }
}
