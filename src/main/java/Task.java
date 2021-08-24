/**
 * The Task class encapsulates a task with a String and
 * whether the task is done or not.
 *
 * @author Pawandeep Singh
 * @version Level-3
 *
 * */
public class Task {

    private char taskType;
    private String taskName;
    private String datetime;
    private boolean isDone;


    public Task(String taskName, TaskType type) {
        this.taskName = taskName;
        this.taskType = TaskType.getTask(type);
        this.isDone = false;
    }

    public Task(String taskName, TaskType type, String datetime) {
        this.taskName = taskName;
        this.taskType = TaskType.getTask(type);
        this.isDone = false;
        this.datetime = datetime;
    }

    public String getTaskName() {
        return taskName;
    }

    public boolean isDone() {
        return isDone;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDone(boolean done) {
        this.isDone = done;
    }

    @Override
    public String toString() {
        String taskTypeString = "[" + taskType + "]";
        String mark = isDone ? "[X]" : "[ ]";
        return taskTypeString + mark + " " + this.taskName;
    }
}
