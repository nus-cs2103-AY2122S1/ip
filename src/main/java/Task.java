public abstract class Task {
    protected final String description;
    protected boolean isDone;

    public Task(String description){
        this.description = description;
        this.isDone = false;
    }

    public abstract String checkStatus();

    public String showDescription(){
        return this.description;
    }

    public void markDone(){
        isDone = true;
    }

}
