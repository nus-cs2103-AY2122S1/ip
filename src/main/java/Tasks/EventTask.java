package tasks;

public class EventTask extends Task {
    String at;
    public EventTask(String description, String at) {
        super(description, TaskType.EVENT);
        this.at = at;
    }

    public String getDate(){
        return at;
    }

    @Override
    public String toString(){
        String typeString = type == TaskType.TODO ? "T" : type == TaskType.EVENT? "E" : "D";
        String doneSymbol = isDone? "X" : " ";
        String result = "[" + typeString + "] " + "[" + doneSymbol + "] " + name + " (at: " + at + ")";
        return result;
    }
}
