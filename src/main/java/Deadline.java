public class Deadline extends Task{
    protected String by;

    public Deadline(String description, String by){
        super(description);
        this.by = by;
    }

    @Override
    public String checkStatus() {
        return "[D]" + (isDone ? "[X]" : "[ ]") + " " + this.showDescription() + " " + this.showDeadline();
    }

    public String showDeadline(){
        return String.format("(by: %s)", this.by);
    }
}
