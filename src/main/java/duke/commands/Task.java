package duke.commands;

public abstract class Task {
    protected final String description;
    protected boolean isDone;
    protected final char representation;

    public Task(String description, boolean isDone, char representation){
        this.description = description;
        this.isDone = isDone;
        this.representation = representation;
    }

    public String checkStatus(){
        return String.format("[%s][%s] %s", representation, (isDone ? 'X' : ' '), description);
    }

    public void markDone(){
        isDone = true;
    }

    @Override
    public String toString(){
        String str = String.format("%s|%c|%s", representation, (isDone ? '1' : '0'), description);
        return str;
    }

}
