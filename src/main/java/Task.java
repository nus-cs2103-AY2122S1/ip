public abstract class Task {
    protected String name;
    protected boolean isDone;
    private boolean isTimed;

    public Task(String name, boolean isTimed){
        this.name = name;
        this.isTimed = isTimed;
        this.isDone = false;
    }

    public Task(String name, boolean isTimed, boolean isDone){
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

    public boolean isTimed(){
        return isTimed;
    }

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