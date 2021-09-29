package Duke.task;

public class Todo extends Task {

    protected String type;

    public Todo(String description) {
        super(description);
        this.type = "Todo";
    }

    public String getType() {
        return type;
    }

    public String addToFile() {
        return "T | 0 | " + this.description;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}