public class Deadline extends Task {
    String time;

    public Deadline(String description, String time) {
        this.description = description;
        this.time = time;
    }

    @Override
    public String toString() {
        return String.format("[D][%c] %s (by: %s)", isDone ? 'X' : ' ', description, time);
    }
}
