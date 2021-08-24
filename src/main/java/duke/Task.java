package duke;

public class Task {
    private boolean done;
    private String title;

    public Task(String title) {
        this.done = false;
        this.title = title;
    }

    @Override
    public String toString() {
        return (done ? "[X]" : "[ ]") + title;
    }

    public void markAsDone() {
        done = true;
    }

    public String writeTask() {
        return (done ? "1" : "0") + " | " + title;
    }

    public String getTitle() {
        return title;
    }
}
