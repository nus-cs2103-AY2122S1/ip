/**
 * The Task class encapsulates a task with a String and
 * whether the task is done or not.
 *
 * @author Pawandeep Singh
 * @version Level-3
 *
 * */
public class Task {
    private String task;
    private boolean isDone;

    public Task(String task) {
        this.task = task;
        this.isDone = false;
    }

    public void setDone(boolean done) {
        this.isDone = done;
    }


    @Override
    public String toString() {
        String mark = isDone ? "[X]" : "[ ]";
        return mark + " " + this.task;
    }
}
