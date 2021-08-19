public class Task {

    private String itemName;
    private boolean isDone = false;

    public Task(String str) {
        itemName = str;
    }

    public String getItemName() {
        return itemName;
    }

    public boolean getIsDone() {
        return isDone;
    }

    public void changeIsDone(boolean bool) {
        isDone = bool;
    }

    @Override
    public String toString() {
        return "[" + (isDone ? "X" : " ") + "] " + itemName;
    }
}