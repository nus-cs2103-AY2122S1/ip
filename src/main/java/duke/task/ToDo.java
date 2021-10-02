package duke.task;

public class ToDo extends Task{
    private boolean isDone;
    private String taskString;
    private int refId;

    public ToDo(String taskString) {
        this.taskString = taskString;
        this.isDone = false;
        this.refId = -1;
    }

    @Override
    public void markAsDone() {
        this.isDone = true;
    }

    @Override
    public void setRefId(int n) {
        this.refId = n;
    }

    @Override
    public int getRefId() {
        return this.refId;
    }

    @Override
    public String getTaskString() {
        if(isDone) {
            return "[T][X] " + this.taskString;
        } else {
            return "[T][ ] " + this.taskString;
        }
    }
}
