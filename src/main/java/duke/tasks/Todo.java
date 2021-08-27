package main.java.duke.tasks;
public class Todo extends Task{
    protected String prefix;

    /**
     * Constructs a new todo task
     * @param name name of the task
     */
    public Todo(String name) {
        super(name);
        this.prefix = "[T]";
    }

    /**
     * show the prefix of the todo
     * 
     * @return the prefix 
     */
    @Override
    public String showPrefix() {
        return this.prefix;
    }

    @Override
    public String toString() {
        return (this.prefix + " " + super.showStatus() + this.name);
    }

    /**
     * print out the relevant info of the todo
     */
    @Override
    public void showThisTask() {
        System.out.println(this.prefix + super.showStatus() + this.name);
    }
}
