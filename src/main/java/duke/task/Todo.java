package duke.task;

public class Todo extends Task{

    public Todo(String content) {
        super(content.substring(5).trim());
    }

    @Override
    public String toString() {
        return String.format("[T][%s] %s",
                getStatus() ? "X" : " ", getContent());
    }

    @Override
    public String record() {
        return String.format("T | %s | %s",
                getStatus() ? "1" : "0", getContent());
    }
        
    public boolean hasSchedule() {
        return false;
    }

    @Override
    public String getType() {
        return "T";
    }
}
