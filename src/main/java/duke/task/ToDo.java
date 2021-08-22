package duke.task;

public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
        this.type = "ToDo";
    }

    public String getFormat() {
        return "todo {description}";
    }
}