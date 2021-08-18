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
    public void showThisTask() {
        System.out.println(this.prefix + super.showStatus() + this.name);
    }

    @Override
    public void showThisTask(int num) {
        System.out.println(num +  "."+ this.prefix + showStatus() + this.name);
    }

    

}
