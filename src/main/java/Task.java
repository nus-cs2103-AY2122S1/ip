public abstract class Task {
    protected Boolean done;
    protected String name;
    protected char type;

    public Task(String input) {
        this.name = input;
        this.done = false;
    }

    public String getStatus() {
        return (done ? "X" : " ");
    }

    public String completeTask() {
        this.done = true;
        return "Swee la! You completed this task: \n      " + name;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return "[" + getStatus() + "] " + name;
    }
}
