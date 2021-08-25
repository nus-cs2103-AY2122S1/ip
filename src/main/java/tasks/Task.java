package tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import bot.TaskType;

public abstract class Task {

    public boolean done = false;
    public static final DateTimeFormatter INPUT_TIME_FORMAT = DateTimeFormatter.ofPattern("d/MM/yyyy kkmm"); // kkmm
    public static final DateTimeFormatter OUTPUT_TIME_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy - hh mm a");

    /**
     * Mark a task as complete
     */
    public void markDone() {
        this.done = true;
    }

    /**
     * Get completion status of task
     *
     * @return true iff task is complete
     */
    public boolean getTaskDone() {
        return this.done;
    }

    public String serialize() {
        return String.format("%s,%b,%s,%s", this.getTaskType(), this.getTaskDone(), this.getTaskText(), this.getTaskTime());
    }

    public static Task deserialize(String s) {
        String[] parts = s.split(",");
        TaskType taskType = TaskType.valueOf(parts[0]);

        Task task;
        LocalDateTime taskTime;

        switch (taskType) {
            case Deadline:
                taskTime = LocalDateTime.parse(parts[3], OUTPUT_TIME_FORMAT);
                task = new DeadlineTask(parts[2], taskTime);
                break;
            case Event:
                taskTime = LocalDateTime.parse(parts[3], OUTPUT_TIME_FORMAT);
                task = new EventTask(parts[2], taskTime);
                break;
            case Todo:
                task = new TodoTask(parts[2]);
                break;
            default:
                task = new GeneralTask("");
        }

        if (Boolean.parseBoolean(parts[1])) {
            task.markDone();
        }
        return task;
    }

    @Override
    public String toString() {
        String taskChecked = this.done ? "X" : " ";
        return String.format("[%s][%s] %s", this.getTaskType().getSymbol(), taskChecked, this.getTaskDesc());
    }

    /**
     * Get a task's descrption details
     *
     * @return task decsription string
     */
    abstract String getTaskDesc();

    abstract String getTaskText();

    abstract String getTaskTime();

    abstract TaskType getTaskType();
}
