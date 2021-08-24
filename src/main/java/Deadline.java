public class Deadline extends Task {
    protected String time;
    public Deadline(String name, String time){
        super(name);
        this.time = time;
    }

    public Deadline(String name, String time, boolean isDone){
        super(name, isDone);
        this.time = time;
    }

    @Override
    public String toCsvRow() {
        return String.join(",", name, time, String.valueOf(isDone));
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
