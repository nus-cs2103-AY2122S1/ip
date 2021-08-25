/**
 * @author Hang Zelin
 * @description ToDos class that extends Task class. It is one of the types in 3 tasks.
 */
package Duke.Task;

public class ToDos extends Task {

    private boolean done = false;
    private String task = "";
    private String taskType = "T";

    public ToDos(boolean done, String task) {
        this.done = done;
        this.task = task;
    }

    @Override
    public String getTaskInfo() {
        String done_str = "";
        if (!this.done) {
            done_str = " ";
        } else {
            done_str = "X";
        }

        return "[" + taskType + "]" + "[" + done_str + "] " + task;
    }

    @Override
    public String ParsedTime() {
        return null;
    }

    @Override
    public String getTime() {
        return null;
    }

    @Override
    public void MarkDone() {
        this.done = true;
    }

    @Override
    public String getSaveDataInfo() {
        return this.taskType + " | " + (this.done ? 1 : 0) + " | " + task;
    }
}
