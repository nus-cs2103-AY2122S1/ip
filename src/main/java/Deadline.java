public class Deadline extends Task{
    String by;
    Deadline(String name,String by){
        super(name);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + (this.done? "[X] " : "[ ] ")
                + this.name
                +String.format("(by: %s)",this.by);
    }
}
