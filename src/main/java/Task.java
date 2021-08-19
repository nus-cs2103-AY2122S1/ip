public class Task implements Comparable<Task> {
    private static int count = 0;
    private boolean completed;
    private final String name;
    private final int id;

    public Task(String name) {
        this.name = name;
        this.id = count++;
    }

    public String completeTask() {
        this.completed = true;
        return this.toString();
    }

    @Override
    public String toString() {
        if (this.completed) {
            return "[X] " + this.name;
        } else {
            return "[ ] " + this.name;
        }
    }

    @Override
    public int compareTo(Task other) {
        return Integer.compare(this.id, other.id);
    }
}
