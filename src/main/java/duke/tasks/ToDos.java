package duke.tasks;
public class ToDos extends Task {
    public ToDos(String description) {
        super(description);
    }

    public ToDos(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String getSymbol() {
        return "T";
    }

    @Override
    public ToDos markAsDone() {
        return new ToDos(this.getDescription(), true);
    }

    @Override
    public String toString() {
        return "[T][" + this.getStatusIcon() + "] " + this.getDescription();
    }
}
