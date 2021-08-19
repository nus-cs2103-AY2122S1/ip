public class ToDo extends Task{
    private boolean done;
    private String taskString;

    public ToDo(String taskString) {
        this.taskString = taskString;
        this.done = false;
    }

    public void markAsDone() {
        this.done = true;
    }

    public String getTaskString() {
        if(done) {
            return "[T][X] " + this.taskString;
        } else {
            return "[T][ ] " + this.taskString;
        }
    }
}
