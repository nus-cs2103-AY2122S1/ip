public class Task {
    private String title;
    private boolean status;
    private int number;
    private static int itemCounter = 1;

    public Task(String title) {
        this.title = title;
        this.status = false;
        this.number = itemCounter;
        itemCounter++;
    }

    public String isDone() {
        if (!status) {
            return "[ ] ";
        } else {
            return "[X] ";
        }
    }

    public void markAsDone() {
        this.status = true;
    }

    public String getTitle() {
        return title;
    }

    public int getNumber() {
        return number;
    }

}
