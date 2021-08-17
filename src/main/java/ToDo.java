import java.lang.String;

public class ToDo extends Task{

    private final String TYPE = "T";

    ToDo(String taskTitle){
        super(taskTitle);
    }

    @Override
    public String toString(){
        return String.format("[%s][%s] %s", this.TYPE,(this.isDone ? "X" : " "), this.taskTitle);
    }
}