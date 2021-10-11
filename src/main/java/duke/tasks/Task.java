package duke.tasks;

public abstract class Task {
    private String description;
    private boolean isDone;

    /**
     * Constructor for new tasks.
     * @param description Description of the task 
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructor for rebuilding TaskList while parsing the duke.txt file.
     * @param description
     * @param isDone
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public boolean getCompletionStatus() {
        return this.isDone;
    }

    public String getDescription() {
        return this.description;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markTaskDone() {
        this.isDone = true;
    }

    /**
     * Generates String of task to be saved into duke.txt
     * @return String format of task
     */
    public String saveText() {
        String isDone = this.isDone ? "1" : "0";
        return isDone + " / " + this.description;
    }

    /**
     * Generates String of task to be displayed to user
     * @return String format of task
     */
    @Override
    public String toString() {
        String taskStr = String.format("[%s] ", getStatusIcon())
                + this.description;
        return taskStr;
    }
}
