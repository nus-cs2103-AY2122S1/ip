package duke.tasks;

public class ToDos extends duke.tasks.Task {

    public ToDos(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String persistedDataStringFormat() {
        char isDone01 = this.isDone ? '1' : '0';
        return String.format("T,%c,%s", isDone01, this.description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }


}
