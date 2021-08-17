import java.lang.String;


public class Event extends Task{

    private final String TYPE = "E";
    private String time;

    Event(String tasktitle, String time){
        super(tasktitle);
        this.time = time;
    }

    @Override
    public String toString(){
        return String.format("[%s][%s] %s (at: %s)", this.TYPE, (this.isDone ? "X" : " "), this.taskTitle, this.time);
    }
}
