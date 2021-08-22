public class Event extends Task {
    private String schedule;

    public Event(String description, String s) {
        super(description);
        this.schedule = s;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at:" + this.schedule + ")";
    }


    @Override
    public String toStringConvert(){
        if(this.isCompleted()) {
            return "E | 1 | " +  this.getString() + "|" + this.schedule;
        } else {
            return "E | 0 | " +  this.getString() + "|" + this.schedule;
        }
    }
}