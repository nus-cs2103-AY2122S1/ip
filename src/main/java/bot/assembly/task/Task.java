package bot.assembly.task;

import java.time.LocalDateTime;

public abstract class Task {

    private String taskTitle;
    private boolean isDone;
    private String taskType;

    /**
     * Constructor
     * @param taskTitle
     */
    public Task (String taskTitle, String taskType) {
        this.taskTitle = taskTitle;
        this.isDone = false;
        this.taskType = taskType;
    }

    /**
     * Constructor
     * @param taskTitle
     * @param isDone
     * @param taskType
     */
    public Task (String taskTitle, boolean isDone, String taskType) {
        this.taskTitle = taskTitle;
        this.isDone = isDone;
        this.taskType = taskType;
    }

    /**
     * Returns the title of the task in String
     * @return
     */
    public String getTaskTitle() {
        return this.taskTitle;
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    public abstract LocalDateTime getDateTime();

    /**
     * Returns the type of the task in String
     * @return
     */
    public String getTaskType() {
        return this.taskType;
    }

    /**
     * Sets isDone to true
     */
    public void markAsDone() {
        this.isDone = true;
    }
}
