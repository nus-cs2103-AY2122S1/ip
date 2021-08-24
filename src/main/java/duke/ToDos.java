package duke;

public class ToDos extends Task {
    private String type;

    public ToDos(String title) {
        super(title);
        this.type = "T";
    }

    @Override
    public String toString() {
        return "[" + type + "]" + super.toString();
    }

    @Override
    public String writeTask() {
        return type + " | " + super.writeTask();
    }
}
