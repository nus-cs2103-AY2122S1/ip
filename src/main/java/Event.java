public class Event extends Task{
    protected String deadline;

    Event(String desc, String deadline) {
        super(desc);
        this.deadline = deadline;
    }

    public String getDeadline() {
        return this.deadline;
    }

    @Override
    public String toString(){
        return "[E]" + "[" + this.getStatusIcon() + "]" + " " + this.description + "(" + deadline + ")";
    }
}
