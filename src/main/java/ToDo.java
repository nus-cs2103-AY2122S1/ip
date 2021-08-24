import java.lang.String;

public class ToDo extends Task{

    /**
     * Constructor
     * @param taskTitle
     */
    ToDo (String taskTitle){
        super(taskTitle, "T");
    }

    ToDo (String taskTitle, boolean isDone) {
        super(taskTitle, isDone, "T");
    }

    @Override
    public String getTime() {
        return null;
    }

    /**
     * Returns the status of the task with its title
     * @return String task status (formatted)
     */
    @Override
    public String toString(){
        return String.format("[%s][%s] %s",
                this.getTaskType(),
                (this.isDone ? "X" : " "),
                this.taskTitle);
    }
}