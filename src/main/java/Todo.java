public class Todo extends Task{
    protected String prefix;

    public Todo(String name) {
        super(name);
        this.prefix = "[T]";
    }

    @Override
    public String showPrefix() {
        return this.prefix;
    }

    @Override
    public void addThisTask() {
        System.out.println("Got it. I've added this task: \n" + this.prefix + super.showStatus() + this.name);
    }

}
