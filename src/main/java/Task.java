import java.util.Arrays;

public class Task {
    protected String type;
    protected String time;
    protected String description;
    protected boolean isDone;

    public Task(String type,String description) {
        this.type = type;
        if (!type.contains("todo")) {
            //split description into main description and time
            this.description = description.split("/")[0];
            this.time ="(" + description.split("/")[1] + ")";
        } else {
            this.description = description;
        }
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getType() {
        return type.contentEquals("todo") ? "T" : type.contentEquals("deadline") ? "D" : "E" ; // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
    }

    @Override
    public String toString(){
        return String.format("[%s][%s] %s %s\n",this.getType(), this.getStatusIcon() , this.description, this.time == null ? "" : this.time);
    }
}

