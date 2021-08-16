public class Task {
    private String name;
    private boolean done;

    public Task(String name) {
        this.name = name;
        this.done = false;
    }

    public String getDone() {
        return this.done ? "X" : " ";
    }

    public String getName() {
        return this.name;
    }

    public void completeTask() {
        this.done = true;
    }

    @Override
    public String toString() {
        return "[" + this.getDone() + "] " + this.getName();
    }

}
