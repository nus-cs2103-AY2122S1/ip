package tasks;

public class DeadlineTask extends Task{
    String by;
    public DeadlineTask(String description, String by) {
        super(description, TaskType.DEADLINE);
        this.by = by;
    }

    @Override
    public String toString(){
        String typeString = type == TaskType.TODO ? "T" : type == TaskType.EVENT? "E" : "D";
        String doneSymbol = isDone? "X" : " ";
        String result = "[" + typeString + "] " + "[" + doneSymbol + "] " + name + "(by: " + by + ")";
        return result;
    }

}
