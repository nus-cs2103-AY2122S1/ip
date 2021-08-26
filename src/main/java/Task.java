package duke.task;

/**
 * Task is the abstract base class of all the Tasks.
 */

public abstract class Task {
    private int done;
    private String toDo;

    Task(String toDo) {
        this(0, toDo);
    }

    Task(int done, String toDo){
        this.done = done;
        this.toDo = toDo;
    }

    /**
     * Changes the done variable stored in Task to 1, representing a completed task.
     */

    public void complete(){
        this.done = 1;
    }

    String getToDo() {
        return toDo;
    }

    String getDone() {
        return String.valueOf(done);
    }

    /**
     * returns a String in the format "Completion Status | Task"
     * @return a String in the format "Completion Status | Task"
     */

    public String getToWrite() {
        return  this.getDone() + " | "  + this.getToDo();
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Task)){
            return false;
        } else {
            Task oTask = (Task) o;
            return this.toString().equals(oTask.toString());
        }
    }

    @Override
    public String toString(){
        if(done == 1){
            return String.format("[X] %s", toDo);
        } else {
            return String.format("[ ] %s", toDo);
        }
    }

}
