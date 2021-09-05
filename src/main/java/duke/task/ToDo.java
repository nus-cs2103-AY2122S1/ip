package duke.task;

public class ToDo extends Task {
    public ToDo(String title) {
        super(title, Type.TODO);
    }

    public ToDo(String title, boolean isDone) {
        super(title, Type.TODO);
        this.isDone = isDone;
    }
}
