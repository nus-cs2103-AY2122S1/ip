package ailurus.task;

import java.io.FileWriter;

import ailurus.AilurusException;


public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for Tasks
     *
     * @param description description of task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Getting whether task is done or not
     *
     * @return "X" if it is done and " " if not done
     */
    public String getStatusIcon() {
        return (this.isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Setting task as done
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Logs the task by writing data to storage.
     *
     * @param writer FileWriter to be written into.
     */
    public abstract void log(FileWriter writer);

    /**
     * Get the description of the task
     *
     * @return description of task
     */
    public String getDescription() {
        return this.description;
    }

    public abstract void handleException(String... arr) throws AilurusException;

    @Override
    public String toString() {
        return String.format("[%s]%s", this.getStatusIcon(), this.description);
    }
}
