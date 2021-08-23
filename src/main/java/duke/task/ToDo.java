package duke.task;

public class ToDo extends Task {

    private ToDo(String description) {
        super(description);
    }

    public static Task of(boolean isDone, String description) {
        Task ret = new ToDo(description);
        return isDone ? ret.done() : ret;
    }

    public String getTaskType() {
        return "T";
    }

    @Override
    public String toString() {
        return String.format("[%s]%s", this.getTaskType(), super.toString());
    }
}
