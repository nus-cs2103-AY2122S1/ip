package duke.task;

public class Task {
    Boolean done;
    String description;

    public Task(String str) {
        done = false;
        description = str;
    }

    public Task(String isDone, String str) {
        done = !isDone.equals("0");
        description = str;
    }

    public void markDone() {
        done = true;
    }

    @Override
    public String toString() {
        return "[" + (done ? 'X' : ' ') + "] " + description;

    }

    public String saveString() {
        return (done ? "1" : "0") + "|" + description;
    }
}
