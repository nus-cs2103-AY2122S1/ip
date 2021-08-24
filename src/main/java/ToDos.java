public class ToDos extends Task{

    private boolean done = false;
    private String task = "";
    private String taskType = "T";

    public ToDos(boolean done, String task) {
        super(done,task);
        this.done = done;
        this.task = task;
    }

    @Override
    public String PrintTaskInfo() {
        String done_str = "";
        if (!this.done) {
            done_str = " ";
        } else {
            done_str = "X";
        }

        return "[" + taskType + "]" + "[" + done_str + "] "  + task;
    }

    @Override
    public void MarkDone() {
        this.done = true;
    }

    @Override
    public String GetDataInfo() {
        return this.taskType + " | " + (this.done? 1 : 0) + " | " + task;
    }
}
