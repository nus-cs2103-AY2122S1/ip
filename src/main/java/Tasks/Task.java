package tasks;

public class Task {

    protected String name;
    protected boolean isDone = false;
    TaskType type;

    public enum TaskType {
        TODO, DEADLINE, EVENT
    }

    public Task(String name, TaskType type) {
        this.name = name;
        this.type = type;
    }

    public boolean isDone(){
        return isDone;
    }
    public void makeDone(){
        isDone = true;
    }

    @Override
    public String toString(){
        String typeString = type == TaskType.TODO ? "T" : type == TaskType.EVENT? "E" : "D";
        String doneSymbol = isDone? "X" : " ";
        String result = "[" + typeString + "] " + "[" + doneSymbol + "] " + name;
        return result;
    }

}
