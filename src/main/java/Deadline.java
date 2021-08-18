public class Deadline extends Task {
    String ddl;

    public Deadline(String content, String time) {
        super(content);
        this.ddl = time;
    }

    public String toString() {
        return String.format("[D] [%s] %s (by: %s)",
                isDone ? "X" : " ", content, ddl);
    }
}
