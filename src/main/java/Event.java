public class Event extends Task {

    protected String at;

    public Event(String description, boolean isDone, String at){
        super(description, isDone, TaskType.EVENT);
        this.at = at;
    }

    @Override
    public String getStatus(){
        return "E" + super.getStatus() + " (at: " + at + ")";
    }

    @Override
    public String toString() {
        return "E" + " | " + super.toString() + " | " + at;
    }
}
