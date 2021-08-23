package duke.tasks;

public class Todo extends Task {
    public Todo(String desc) {
        super(desc);
    }
    public Todo(String entry, boolean done) {
        super(entry, done);
    }
    
    void addTime(String rawArgs) {}

    public String toDB() {
        return String.format("T | %d | %s ", super.done ? 1 : 0, super.desc);
    }

    @Override
    public String toString() {
        return "[T]" + (done ? "[X] " : "[ ] ") + desc;
    }
}
