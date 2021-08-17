public class ToDos extends Task{

    private boolean done = false;
    private String task = "";

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

        return "[" + "T" + "]" + "[" + done_str + "] "  + task;
    }

    @Override
    public void MarkDone() {
        this.done = true;
    }

}
