public class Event extends Task{
    protected String time;
    protected String type;

    public Event(String information,String time,String type){
        super(information);
        this.type = type;
        this.time = time;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + time + ")";
    }

    public String getDetails() {
        return time;
    }

    public String getType() {
        return type;
    }
}