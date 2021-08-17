public class Event extends Task{
    protected String prefix;
    protected String time;

    public Event(String name, String time) {
        super(name);
        this.prefix = "[E]";
        this.time = time;
    }

    @Override
    public String showPrefix() {
        return this.prefix;
    }

    @Override
    public void addThisTask() {
        System.out.println("Got it. I've added this task: \n" + this.prefix + super.showStatus() + this.name + "(at:" + this.time + ")");
    }
}
