public class Task {
    protected boolean isDone;
    protected String description;

    public Task() {
        setNotDone();
    }

    public Task(String description){
        setNotDone();
        setDescription(description);
    }

    protected void setDescription(String in){
        this.description = in;
    }

    public Task setDone(){
        this.isDone = true;
        return this;
    }

    public Task setNotDone(){
        this.isDone = false;
        return this;
    }

    public String getDescription(){
        return this.description;
    }

    @Override
    public String toString(){
        String state = isDone ? "[X] " : "[ ] ";
        return state + this.description;
    }
}

