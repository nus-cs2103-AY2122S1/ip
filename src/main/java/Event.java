public class Event extends Task{
    private String at;

    public Event(String name, String at) throws DukeException {
        super(name);
        this.at = at;
    }

    @Override
    public String saveTask(){
        return "EVENT|" + this.getName() +"/at" + at + (this.isDone() ? "|1" : "|0");
    }
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
