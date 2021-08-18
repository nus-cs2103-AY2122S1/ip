public class Deadline extends Task {
    protected String time;
    public Deadline(String name, String time){
        super(name);
        this.time = time;
    }

    @Override
    public String toString(){
        if (this.isDone){
            return "[D][X] " + name + " (by: " + time + ")";
        }
        else {
            return "[D][ ] " + name + " (by: " + time + ")";
        }
    }
}
