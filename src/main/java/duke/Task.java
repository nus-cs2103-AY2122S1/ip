package duke;

public class Task {
    protected String description;
    protected boolean isDone;
    protected int number;

    public Task(String description,int number) {
        this.description = description;
        this.isDone = false;
        this.number = number;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public String getDescription() {
        return this.description;
    }

    public void markAsDone(){
        this.isDone = true;
    }
    
    @Override
    public String toString(){
        return "[" + this.getStatusIcon() + "] " + this.description ;
    }
}