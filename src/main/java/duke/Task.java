package duke;

public class Task {
    private boolean done;
    private String title;

    Task(String title) {
        this.done = false;
        this.title = title;
    }

    @Override
    public String toString() {
        return (done ? "[X]" : "[ ]") + title;
    }

    void markAsDone() {
        done = true;
    }

    String writeTask() {
        return (done ? "1" : "0") + " | " + title;
    }

}
