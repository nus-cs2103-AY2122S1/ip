public class Task {
    private boolean isDone;
    private String description;

    public Task(String description){
        setNotDone();
        this.description = description;
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

