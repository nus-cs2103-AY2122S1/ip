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
        System.out.println(this.prefix + "[X] " + this.name);
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

    public void showThisTask() {
        System.out.println(this.prefix + showStatus() + this.name);
    }

    public void showThisTask(int num) {
        System.out.println(num +  "."+ this.prefix + showStatus() + this.name);
    }
}
