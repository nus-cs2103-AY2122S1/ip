package duke.task;

import duke.exceptions.UnclearInstructionException;

public class Task {
    protected String description;
    protected boolean isDone;
    
    /**
     * Constructor method of Task.
     *
     * @param description Description of a task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;

    }

    /**
     * Constructor method of Task.
     *
     * @param description Description of a task.
     * @param isDone Done status of a task.                   
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;

    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Returns doneIndicator as "1" or "0".
     *
     * @return task doneIndicator.
     */
    public String getDoneIndicator() {
        String doneIndicator;
        if (isDone == false) {
            doneIndicator = "0";
        } else {
            doneIndicator = "1";
        }
        return doneIndicator;
    }


    public String getDescription() {
        return this.description;
    }


    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
    
//    public String toFileString() {
//        return "|" + this.getDoneIndicator() + "|" + this.description;
//    }
    public String toFileString() {
        return "/" + this.description + "/" + this.getDoneIndicator();
    }
    
    /**
     * Marks task status as done.
     */
    public void maskAsDone() {
        isDone = true;
    }
    
}
