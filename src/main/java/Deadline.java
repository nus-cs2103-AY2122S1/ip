public class Deadline extends Task{
    protected String by;

    public Deadline(String description, String by){
        super(description);
        this.by = by;
    }

    @Override
    public String checkStatus() {
        return (isDone ? "[D]" : "[]" + " " + this.showDescription());
    }

    public String showDeadline(){
        return String.format("(%s)", this.by);
    }
}
