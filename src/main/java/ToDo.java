public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String getTypeIndicator() {
        return "[T]";
    }

    @Override
    public String toFileRecord() {
        return String.format("T | %d | %s",
                this.isDone ? 1 : 0 , this.description);
    }
}


