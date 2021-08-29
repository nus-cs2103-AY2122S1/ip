package task;

/**
 * Template for a task: event, deadline, todo
 * Allows users to get_type and get_task
 */

public class Task {
    public String type;
    public String date_time;
    public String message;


    public Task (
            String message) {
        this.message = message;
        }

    public Task (
            String message,
                boolean isDuke) {}

    public String getType() {
        return this.type;
    }

    public void setType() {
        this.type = "";
    }

    public void setTask() {
    }

    public void setTask2() {
    }

    public String getTask() {
        return "";
    }

    public void setDateTime() {
        this.date_time = "";
    }

    public String getDateTime() {
        return date_time;
    }

}

