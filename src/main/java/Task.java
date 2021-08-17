public class Task {

    private boolean done = false;
    private String task = "";

    public Task(boolean done, String task){
        this.done = done;
        this.task = task;
    }

    public String PrintTaskInfo(){
        String done_str = "";
        if (!this.done) {
            done_str = " ";
        } else {
            done_str = "X";
        }

        return "[" + done_str + "] "  + task;
    }

    public void MarkDone() {
        this.done = true;
    }

}
