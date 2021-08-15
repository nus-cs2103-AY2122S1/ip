public class Task {
    private final String name;
    private boolean completed;

    public Task(String name) {
        this.name = name;
        completed = false;
    }

    public void addResponse(int size) {
        System.out.println("Got it. I've added this task: \t");
        System.out.println(this.toString());
        System.out.println("Now you have " + size + " tasks in your list.");
    }

    public String getName() {
        return this.name;
    }
    public void toggleCompleted() {
        this.completed = !this.completed;
    }
    public String toString() {
        return "[" + (completed ? "X" : " ") + "] " + this.name;
    }
}
