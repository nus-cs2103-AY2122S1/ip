public class Task {
    private String description;
    private boolean status;
    private int itemId;
    private static int itemCounter = 1;

    public Task(String description) {
        this.description = description;
        this.status = false;
        this.itemId = itemCounter;
        itemCounter++;
    }

    public String getStatusIcon() {
        if (!status) {
            return "[ ] ";
        } else {
            return "[X] ";
        }
    }

    public void markAsDone() {
        this.status = true;
    }

    public String getDescription() {
        return description;
    }

    public int getNumber() {
        return itemId;
    }

    @Override
    public String toString() {
        return this.getStatusIcon() + " " + this.description;
    }

}
