import java.lang.String;


public class Deadline extends Task{
    private final String TYPE = "D";
    private String time;

    Deadline(String taskTitle, String time){
        super(taskTitle);
        this.time = time;
    }

    @Override
    public String toString(){
        return String.format("[%s][%s] %s (by: %s)", this.TYPE, (this.isDone ? "X" : " "), this.taskTitle, this.time);
    }
}
