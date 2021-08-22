public class Event extends Task{
    protected String duration;

    Event(String desc, String deadline) {
        super(desc);
        this.duration = deadline;
    }

    @Override
    public String toString(){
        return "[E]" + "[" + this.getStatusIcon() + "]" + " " + this.description + " (at: " + duration + ")";
    }

    @Override
    public String save() {
            return "E | " + (this.isDone ? 1 : 0) + " | " + this.description + " | " +  this.duration;
    }
}
