public class Event extends Task {
    protected String time;

    public Event(String name, String time){
        super(name);
        this.time = time;
    }

    @Override
    public String toString(){
        return String.format("[E][%s] %s (at: %s)",
                this.isDone ? "X" : " ", name, time);
    }
}
