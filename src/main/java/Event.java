public class Event extends Task{
    protected String prefix;
    protected String time;

    /**
     * Constructs a new event task.
     * 
     * @param name name of the task
     * @param date date of the task
     */
    public Event(String name, String time) {
        super(name);
        this.prefix = "[E]";
        this.time = time;
    }

    /**
     * show the prefix of the event
     * 
     * @return the prefix 
     */
    @Override
    public String showPrefix() {
        return this.prefix;
    }

    /**
     * print out the relevant info of the event
     */
    @Override
    public void showThisTask() {
        System.out.println(this.prefix + super.showStatus() + this.name + "(at:" + this.time + ")");
    }

    /**
     * print out the relevant info of the event with number in front
     * 
     * @param num the index of the event in the task list
     */
    @Override
    public void showThisTask(int num) {
        System.out.println(num +  "."+ this.prefix + showStatus() + this.name + "(at:" + this.time + ")");
    }

    /**
     * mark an event as done and print out relevant information.
     */
    @Override
    public void markAsDone() {
        this.hasDone = true;
        System.out.println(this.prefix + "[X] " + this.name + "(by:" + this.time + ")");
    }
}
