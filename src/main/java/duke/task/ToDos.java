/**
 * @author Hang Zelin
 * @description ToDos class that extends Task class. It is one of the types in 3 tasks.
 */
package duke.task;

public class ToDos extends Task {

    private boolean done;
    private String task;
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
    public String parsedTime() {
        return null;
    }

    @Override
    public String getTime() {
        return null;
    }

    @Override
    public void markDone() {
        this.done = true;
    }

    @Override
    public String getSaveDataInfo() {
        return this.taskType + " | " + (this.done ? 1 : 0) + " | " + task;
    }
}
