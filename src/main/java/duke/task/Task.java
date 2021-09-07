package duke.task;

/**
 * Task is the abstract base class of all the Tasks.
 */
public abstract class Task {
    private boolean isDone;
    private String toDo;

    Task(String toDo) {
        this(false, toDo);
    }

    Task(boolean isDone, String toDo) {
        this.isDone = isDone;
        this.toDo = toDo;
    }

    /**
     * Changes the done variable stored in Task to 1, representing a completed task.
     */
    public void complete() {
        this.isDone = true;
    }

    String getToDo() {
        return toDo;
    }

    String getDone() {
        if (!isDone) {
            return "0";
        }
        return "1";
    }

    /**
     * returns a String in the format "Completion Status | Task"
     *
     * @return a String in the format "Completion Status | Task"
     */
    public String getToWrite() {
        return this.getDone() + " | " + this.getToDo();
    }


    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Task)) {
            return false;
        } else {
            Task oTask = (Task) o;
            return this.toString().equals(oTask.toString());
        }
    }

    @Override
    public String toString() {
        if (isDone) {
            return String.format("[X] %s", toDo);
        } else {
            return String.format("[ ] %s", toDo);
        }
    }

}
