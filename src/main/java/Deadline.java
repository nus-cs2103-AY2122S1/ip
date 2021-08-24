public class Deadline extends Task{
    protected String by;

    public Deadline(String description, boolean isDone, String by){
        super(description, isDone, 'D');
        this.by = by;
    }

    @Override
    public String checkStatus() {
        return super.checkStatus() + " " + this.showDeadline();
    }

    public String showDeadline(){
        return String.format("(by: %s)", this.by);
    }

    @Override
    public String toString(){
        return super.toString() + String.format("|%s", by);
    }
}
