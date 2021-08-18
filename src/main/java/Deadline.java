public class Deadline extends Task{
    protected String prefix;
    protected String date;

    /**
     * Constructs a new deadline task.
     * 
     * @param name name of the task
     * @param date date of the task
     */
    public Deadline(String name, String date) {
        super(name);
        this.prefix = "[D]";
        this.date = date;
    }

    /**
     * show the prefix of the deadline
     * 
     * @return the prefix 
     */
    @Override
    public String showPrefix() {
        return this.prefix;
    }

    /**
     * print out the relevant info of the deadline task
     */
    @Override
    public void showThisTask() {
        System.out.println(this.prefix + super.showStatus() + this.name + "(by:" + this.date + ")");
    }

    /**
     * print out the relevant info of the deadline task with number in front
     * 
     * @param num the index of the deadline task in the task list
     */
    @Override
    public void showThisTask(int num) {
        System.out.println(num +  "."+ this.prefix + showStatus() + this.name + "(by:" + this.date + ")");
    }

    /**
     * mark a deadline task as done and print out relevant information.
     */
    @Override
    public void markAsDone() {
        this.hasDone = true;
        System.out.println(this.prefix + "[X] " + this.name + "(by:" + this.date + ")");
    }
}
