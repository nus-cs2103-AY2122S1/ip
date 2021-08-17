public class Task {
    protected boolean isDone;
    protected String item;
    protected String hasCross = "[X]";
    protected String hasNoCross = "[]";

    public Task(String input) {
        isDone = false;
        item = input;
    }

    public void setDone() {
        isDone = true;
    }

    @Override
    public String toString() {
        if (isDone) {
            return hasCross + " " + item;
        } else {
            return hasNoCross + " " + item;
        }
    }
}
