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

    @Override
    public void showThisTask(int num) {
        System.out.println(num +  "."+ this.prefix + showStatus() + this.name + "(at:" + this.time + ")");
    }

    @Override
    public void markAsDone() {
        this.hasDone = true;
        System.out.println(this.prefix + "[X] " + this.name + "(by:" + this.time + ")");
    }
}
