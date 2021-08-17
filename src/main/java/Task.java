public class Task {
    protected String name;
    protected boolean hasDone;
    protected String prefix;

    public Task(String name) {
        this.name = name;
        this.hasDone = false;
        this.prefix = "";
    }

    public void markAsDone() {
        this.hasDone = true;
    }

    public String showStatus() {
        if (hasDone) {
            return "[X] ";
        } else {
            return "[ ] ";
        }
    }

    public String showPrefix() {
        return this.prefix;
    }

    public void addThisTask() {
        System.out.println("Got it. I've added this task: \n" + this.prefix + showStatus() + this.name);
    }
}
