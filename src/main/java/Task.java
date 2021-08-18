public class Task {
    protected String name;
    protected boolean hasDone;
    protected String prefix;

    /**
     * Constructs a new task
     * 
     * @param name the task name
     */
    public Task(String name) {
        this.name = name;
        this.hasDone = false;
        this.prefix = "";
    }

    /**
     * mark a task as done and print out relevant information.
     */
    public void markAsDone() {
        this.hasDone = true;
        System.out.println(this.prefix + "[X] " + this.name);
    }

    /**
     * show the status of the task
     * 
     * @return status symbol of the task
     */
    public String showStatus() {
        if (hasDone) {
            return "[X] ";
        } else {
            return "[ ] ";
        }
    }

    /**
     * show the prefix of the task
     * 
     * @return the prefix 
     */
    public String showPrefix() {
        return this.prefix;
    }

    /**
     * print out the relevant info of the task
     */
    public void showThisTask() {
        System.out.println(this.prefix + showStatus() + this.name);
    }

    /**
     * print out the relevant info of the task with number in front
     * 
     * @param num the index of the task in the task list
     */
    public void showThisTask(int num) {
        System.out.println(num +  "."+ this.prefix + showStatus() + this.name);
    }
}
