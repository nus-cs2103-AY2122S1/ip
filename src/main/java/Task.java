public class Task {
    private boolean isDone;
    private String item;
    public Task(String input) {
        isDone = false;
        item = input;
    }

    public void setDone() {
        isDone = true;
    }

    @Override
    public String toString() {
        String hasCross = "[X]";
        String hasNoCross = "[]";
        if (isDone) {
            return hasCross + " " + item;
        } else {
            return hasNoCross + " " + item;
        }
    }
}
