public class ToDo extends Task{

    public ToDo(String task) {
        super(task, "T");
    }

    @Override
    public String toString() {
        return String.format("[%s][%s] %s", this.getTaskType(), this.getCompletedMarker(), this.getTask());
    }
}
