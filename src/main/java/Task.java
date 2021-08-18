public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description){
        this.description = description;
        this.isDone = false;
    }

    public String checkStatus(){
        return (isDone ? "[X]" : "[ ]") + " " + this.description;
    }

    public void doTask(){
        isDone = true;
    }
}
