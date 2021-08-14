public class DukeTask {
    final String name;
    boolean isDone = false;

    DukeTask(String name) {
        this.name = name;
    }

    public void markAsDone() {
        isDone = true;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", isDone ? "X" : " ", name);
    }
}

