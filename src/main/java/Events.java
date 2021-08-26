public class Events extends Task{

    private boolean done = false;
    private String task = "";
    private String time = "";
    private String taskType = "E";

    public Events(boolean done, String task, String time) {
        super(done, task);
        this.done = done;
        this.task = task;
        this.time = time;
    }

    @Override
    public String PrintTaskInfo() {
        String done_str = "";
        if (!this.done) {
            done_str = " ";
        } else {
            done_str = "X";
        }

        return "[" + taskType + "]" + "[" + done_str + "] "  + task +" (at:" + time + ")";
    }

    @Override
    public void MarkDone() {
        this.done = true;
    }

    @Override
    public String GetDataInfo() {
        return this.taskType + " | " + (this.done? 1 : 0) + " | " + task + " | " + time;
    }


}
