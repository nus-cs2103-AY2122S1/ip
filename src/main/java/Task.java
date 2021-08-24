public abstract class Task {
    protected String name;
    protected boolean isDone;

    public Task(String name){
        this.name = name;
        this.isDone = false;
    }

    public Task(String name, boolean isDone){
        this.name = name;
        this.isDone = isDone;
    }

    public String getName(){
        return name;
    }

    public void markDone(){
        this.isDone = true;
    }

    public abstract String toCsvRow();

    @Override
    public String toString(){
        if (this.isDone){
            return "[X] " + name;
        }
        else {
            return "[ ] " + name;
        }
    }
}