public class Event extends Task{
    protected String time;

    public Event(String information,String time) {
        super(information);
        this.time = time;
    }

    @Override
    public String toString(){
        return "[E]" + super.toString() + "(at: " + time + ")";
    }
}