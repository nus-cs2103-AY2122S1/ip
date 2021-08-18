public class Task {
    private String description;
    private boolean isDone;

    public Task(String description){
        this.description = description;
        this.isDone = false;
    }

    public String checkStatus(){
        return (isDone ? "[X]" : "[ ]") + " " + this.description;
    }

    public String showDescription(){
        return this.description;
    }

    public void markDone(){
        isDone = true;
    }
}
