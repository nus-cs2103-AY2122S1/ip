public class Deadline extends Task{
    protected String prefix;
    protected String date;

    public Deadline(String name, String date) {
        super(name);
        this.prefix = "[D]";
        this.date = date;
    }

    @Override
    public String showPrefix() {
        return this.prefix;
    }

    @Override
    public void addThisTask() {
        System.out.println("Got it. I've added this task: \n" + this.prefix + super.showStatus() + this.name + "(by:" + this.date + ")");
    }

    @Override
    public void showThisTask(int num) {
        System.out.println(num +  "."+ this.prefix + showStatus() + this.name + "(by:" + this.date + ")");
    }

    @Override
    public void markAsDone() {
        this.hasDone = true;
        System.out.println(this.prefix + "[X] " + this.name + "(by:" + this.date + ")");
    }
}
